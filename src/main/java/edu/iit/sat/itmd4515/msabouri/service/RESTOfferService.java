package edu.iit.sat.itmd4515.msabouri.service;

import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Milad
 */
@Startup
@Path ("/delete")
@Stateless
public class RESTOfferService {
    public RESTOfferService(){
        
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage(){
        return "Milad, The Great!";
    }
}
