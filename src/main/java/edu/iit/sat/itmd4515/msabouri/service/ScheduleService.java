package edu.iit.sat.itmd4515.msabouri.service;

import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Startup;
import javax.ejb.Stateless;

/**
 *
 * @author Milad
 */
@Stateless
public class ScheduleService {

    private static final Logger LOG = Logger.getLogger(ScheduleService.class.getName());

    @Schedule(second = "01", minute = "15", hour = "00")
    public void sendEmail() {
        EmailService emailSvc = new 
            EmailService("milad.sabouri@gmail.com", "mil", "mil", "Milad", "Sabouri");
        Thread th = new Thread(emailSvc);
        LOG.info("Starting a new thread to send the email.." + new Date());
        th.start();
        LOG.info("The email was sent successfully" + new Date());
//        System.out.println("Milad, The Greate: " + new Date());
    }
}
