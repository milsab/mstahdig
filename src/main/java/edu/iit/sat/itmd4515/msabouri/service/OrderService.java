/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.service;

import edu.iit.sat.itmd4515.msabouri.domain.OrderFood;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author Milad
 */
@Named
@Stateless
public class OrderService extends AbstractService<OrderFood> {

    public OrderService() {
        super(OrderFood.class);
    }

    @Override
    public List<OrderFood> findAll() {
        return getEntityManager().createNamedQuery("Order.findAll", OrderFood.class).getResultList();
    }
    
    public List<OrderFood> findByUserName(String username){
        return getEntityManager().createNamedQuery("Order.findByUsername", OrderFood.class).setParameter("username", username).getResultList();
    }

    public List<OrderFood> findAll(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
