/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.web;

import edu.iit.sat.itmd4515.msabouri.domain.Buyer;
import edu.iit.sat.itmd4515.msabouri.domain.Offer;
import edu.iit.sat.itmd4515.msabouri.domain.OrderFood;
import edu.iit.sat.itmd4515.msabouri.service.BuyerService;
import edu.iit.sat.itmd4515.msabouri.service.OfferService;
import edu.iit.sat.itmd4515.msabouri.service.OrderService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Milad
 */
@Named
@ViewScoped
public class BuyerController extends AbstractController implements Serializable {

    private static final Logger LOG = Logger.getLogger(BuyerController.class.getName());

    private Buyer buyer;
    private OrderFood order;
    private String qty;

    @EJB
    private BuyerService buyerSvc;

    @EJB
    private OrderService orderSvc;
    
    @EJB
    private OfferService offerSvc;

    @Inject
    private LoginController loginController;

    @PostConstruct
    private void postConstructor() {
        super.postConstruct();
        LOG.info("Inside BuyerController postConstructor");
        buyer = buyerSvc.findByUserName(loginController.getRemoteUser());
        order = new OrderFood();
    }

    // action methods here
    public String doAddOrder(Offer offer) {

        Integer qtyy = offer.getQty();
        if (offer.getQuantity() - qtyy >= 0) {
            BigDecimal price
                    = new BigDecimal(qtyy * (offer.getUnitPrice().intValue()));
            this.order.setOrderDate(new Date());
            this.order.setBuyer(buyer);
            this.order.setOffer(offer);
            this.order.setPrice(price);
            this.order.setQuantity(qtyy);

            orderSvc.create(order);
            offer.setQuantity(offer.getQuantity() - qtyy);
            
            offerSvc.update(offer);

            return "/buyer/order?faces-redirect=true";
        } else{
            context.addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "The Qty not acceptable", 
                    "Please enter a valid Qty less than total available quantity"));
            return "#";
        }

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

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

}