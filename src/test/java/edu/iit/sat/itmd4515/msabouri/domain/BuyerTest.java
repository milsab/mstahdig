/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.domain;

import static edu.iit.sat.itmd4515.msabouri.domain.AbstractJPATest.emf;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Milad
 */
public class BuyerTest extends AbstractJPATest{
    @Before
    public void beforeEachTest() {
        em = emf.createEntityManager();
        tx = em.getTransaction();

    }

    @After
    public void afterEachTest() {

        if (em != null) {
            em.close();
        }
    }
    
    @Test
    public void persitenceSellerTest(){
        Buyer buyer = new Buyer("Nas", "Gha", "F", 
                new GregorianCalendar(2018, 9, 23).getTime());

        OrderFood order = new OrderFood(
                new GregorianCalendar(2018, 9, 23).getTime(), 2, new BigDecimal("20.00"));
        
        order.setBuyer(buyer);
        
        tx.begin();
        em.persist(buyer);
        em.persist(order);
        tx.commit();
        
        Long order_id = order.getOrderId();
        assertNotNull("Persistence of buyer should return buyerId", buyer.getBuyerId());
        Assert.assertEquals("Retrive buyer's name correctly", "Nas", buyer.getFirstName());
        
        OrderFood o = em.find(OrderFood.class, order_id);
        Assert.assertEquals("buyer assigned to the order correctly", "Nas", o.getBuyer().getFirstName());
                
    }
}
