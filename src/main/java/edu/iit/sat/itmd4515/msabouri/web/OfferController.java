/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.web;

import edu.iit.sat.itmd4515.msabouri.domain.Offer;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Milad
 */
@Named
@RequestScoped
public class OfferController {

    private static final Logger LOG = Logger.getLogger(OfferController.class.getName());
    
    private Offer offer;
    
    @PostConstruct
    private void postConstruct(){
        LOG.info("Inside OfferController postConstructor");
        offer = new Offer();
    }
    
    public String executeSaveOffer() {
        LOG.info("Inside OfferController executeSaveOffer");
        return "/admin/welcome.xhtml";
    }
    

    /**
     * Get the value of offer
     *
     * @return the value of offer
     */
    public Offer getOffer() {
        return offer;
    }

    /**
     * Set the value of offer
     *
     * @param offer new value of offer
     */
    public void setOffer(Offer offer) {
        this.offer = offer;
    }

}
