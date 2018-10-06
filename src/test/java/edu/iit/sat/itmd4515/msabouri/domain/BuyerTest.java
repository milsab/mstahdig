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
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
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
        super.beforeEachTest();
    }

    @After
    public void afterEachTest() {

        super.afterEachTest();
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
    
    @Test
    public void FirstNameIsNull(){
        Buyer buyer = new Buyer(null, "abc", "mail", 
                new GregorianCalendar(2018, 9, 23).getTime());
        System.out.println(buyer.toString());
        
        Set<ConstraintViolation<Buyer>> constraintViolations = validator.validate(buyer);
        assertEquals(1, constraintViolations.size());
        
        assertEquals("must not be null", constraintViolations.iterator().next().getMessage());
        
        for(ConstraintViolation<Buyer> bad : constraintViolations){
            System.out.println(bad.toString() + " " + bad.getPropertyPath() + " " + bad.getMessage());
        }
    }
    @Test
    public void LastNameIsNull(){
        Buyer buyer = new Buyer("abc", null, "mail", 
                new GregorianCalendar(2018, 9, 23).getTime());
        System.out.println(buyer.toString());
        
        Set<ConstraintViolation<Buyer>> constraintViolations = validator.validate(buyer);
        assertEquals(1, constraintViolations.size());
        
        assertEquals("must not be null", constraintViolations.iterator().next().getMessage());
        
        for(ConstraintViolation<Buyer> bad : constraintViolations){
            System.out.println(bad.toString() + " " + bad.getPropertyPath() + " " + bad.getMessage());
        }
    }
}
