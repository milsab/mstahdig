/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.web;

import edu.iit.sat.itmd4515.msabouri.domain.OrderFood;
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
public class OrderController {

    private static final Logger LOG = Logger.getLogger(OrderController.class.getName());
    
    private OrderFood order;
    
    @PostConstruct
    private void postConstruct(){
        LOG.info("Inside OfferController postConstructor");
        order = new OrderFood();
        
        
    }

    public OrderFood getOrder() {
        return order;
    }

    public void setOrder(OrderFood order) {
        this.order = order;
    }
}
