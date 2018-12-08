package edu.iit.sat.itmd4515.msabouri.service;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Milad
 * 
 * Following the pattern from https://www.youtube.com/watch?v=4hCZRvJ3vXg
 */
@Path ("/email")
@Stateless
public class RESTEmailService {

    private static final Logger LOG = Logger.getLogger(RESTEmailService.class.getName());
    
    
    EmailService emailSvc;
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void sendMessage(@FormParam("message") String msg, 
            @FormParam("subject") String sub, 
            @FormParam("receiver") String receiver){
        emailSvc = new EmailService(receiver, msg, sub);
        Thread th = new Thread(emailSvc);
        LOG.info("Starting a new thread to send the email..");
        th.start();
        
    }
}
