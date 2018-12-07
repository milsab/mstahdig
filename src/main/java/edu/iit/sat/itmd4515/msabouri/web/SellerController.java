/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.web;

import edu.iit.sat.itmd4515.msabouri.domain.Offer;
import edu.iit.sat.itmd4515.msabouri.domain.Seller;
import edu.iit.sat.itmd4515.msabouri.service.SellerService;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Milad
 */

@Named
@RequestScoped
public class SellerController  extends AbstractController{
    private static final Logger LOG = Logger.getLogger(BuyerController.class.getName());
    
    private Seller seller;
    private Offer offer;
    
    @EJB
    private SellerService sellerSvc;

    @Inject
    private LoginController loginController;
    
    @PostConstruct
    private void postConstructor(){
        super.postConstruct();
        LOG.info("Inside SellerController postConstructor");        
        seller = sellerSvc.findByUserName(loginController.getRemoteUser());
        offer = new Offer();
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
