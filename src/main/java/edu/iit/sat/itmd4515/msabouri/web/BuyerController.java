/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.web;

import edu.iit.sat.itmd4515.msabouri.domain.Buyer;
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
public class BuyerController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(BuyerController.class.getName());
    
    private Buyer buyer;
    
    @Override
    @PostConstruct
    protected void postConstruct(){
        super.postConstruct();
        buyer = new Buyer();       
        LOG.info("Inside BuyerController postConstructor");
    }

    /**
     * Get the value of buyer
     *
     * @return the value of buyer
     */
    public Buyer getBuyer() {
        return buyer;
    }

    /**
     * Set the value of buyer
     *
     * @param buyer new value of buyer
     */
    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

}
