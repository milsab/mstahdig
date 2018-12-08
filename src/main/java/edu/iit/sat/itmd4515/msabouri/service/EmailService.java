package edu.iit.sat.itmd4515.msabouri.service;

import java.util.Properties;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Milad
 *
 * Following the pattern from https://www.javatpoint.com/java-mail-api-tutorial
 */
public class EmailService implements Runnable {

    private static final Logger LOG = Logger.getLogger(EmailService.class.getName());

    private String _to = "";
    private String username = "";
    private String userpass = "";
    private String firstName = "";
    private String lastName = "";
    private String message = "";
    private String subject = "";

    public EmailService(String _to, String username, String userpass, String firstName, String lastname) {
        this._to = _to;
        this.username = username;
        this.userpass = userpass;
        this.firstName = firstName;
        this.lastName = lastname;
    }

    public EmailService(
            String _to,
            String message,
            String subject) {
        this._to = _to;
        this.message = message;
        this.subject = subject;
    }

    @Override
    public void run() {
        if (message.equals("")) {
            sendWithDefaultMessage();
        } else {
            send();
        }
    }

    public void sendWithDefaultMessage() {
        String _from = "meal.sharingservice@gmail.com";
        String _password = "Ng101840";
        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //get Session   
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(_from, _password);
            }
        });
        //compose message    
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(_to));
            message.setSubject("WELCOME TO TAHDIG");
            message.setContent("<body>"
                    + "<h1>TAHDIG</h1>"
                    + "<h3>Dear " + firstName + " " + lastName
                    + "</h3><h2>Thank you for Registering on TAHDIG,</h2>"
                    + "<h3>The First Meal Sharing Platform</h3>"
                    + "<em>Your Username: " + username
                    + "</em><br/><em>Your Password: " + userpass
                    + "</em><br/><a href='http://localhost:8080/msabouri-fp/'>Click here to go to the TAHDIG service</a></body>",
                    "text/html; charset=utf8");
            Transport.send(message);
            LOG.info("The email was sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void send() {
        String _from = "meal.sharingservice@gmail.com";
        String _password = "Ng101840";
        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //get Session   
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(_from, _password);
            }
        });
        //compose message    
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(_to));
            message.setSubject(subject);
            message.setText(this.message);
            Transport.send(message);
            LOG.info("The email was sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTo() {
        return _to;
    }

    public void setTo(String _to) {
        this._to = _to;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
