/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.domain;

import static edu.iit.sat.itmd4515.msabouri.domain.AbstractJPATest.emf;
import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Milad
 */
public class OrderFoodTest extends AbstractJPATest {

    @Before
    @Override
    public void beforeEachTest() {
        super.beforeEachTest();
    }

    @After
    @Override
    public void afterEachTest() {
        super.afterEachTest();
    }

    @Test
    public void persitenceOrderFoodTest() {
        OrderFood order = new OrderFood(
                new GregorianCalendar(2018, 7, 23).getTime(), 1, new BigDecimal("17.00"));

        Offer offer = new Offer("Offer Title", "Offer Description",
                new GregorianCalendar(2018, 7, 23).getTime(), new BigDecimal("20.00"), 10, "", "");

        offer.getOrders().add(order);
        order.setOffer(offer);

        tx.begin();
        em.persist(order);
        em.persist(offer);
        tx.commit();

        //From the inverse side
        Offer findOffer = em.find(Offer.class, 1l);

        System.out.println("From the inverse side... Offer name is: \t" + findOffer.getTitle());
        System.out.println("From the inverse side... OrderFood price is: \t" + findOffer.getOrders().get(0).getPrice());

        //From the owing side
        OrderFood findOrder = em.find(OrderFood.class, 1l);
        System.out.println("From the owing side... Order Price name is: \t" + findOrder.getPrice());
        System.out.println("From the owing side... Order offer name is: \t" + findOrder.getOffer().getTitle());

        // test the inverse collection
        assertTrue(offer.getOrders().size() == 1);

        // test the owning side of the relationship
        assertNotNull(findOrder.getOffer().getTitle());
        assertEquals("Offer Title", findOrder.getOffer().getTitle());
    }
  
    @Test
    public void priceAcceptMinZeroTest() {
        OrderFood order = new OrderFood(
                new GregorianCalendar(2018, 7, 23).getTime(), 
                1, 
                new BigDecimal("-1"));

        System.out.println(order.toString());

        Set<ConstraintViolation<OrderFood>> constraintViolations = validator.validate(order);

        assertEquals(1, constraintViolations.size());

        assertEquals("must be greater than or equal to 0.00", constraintViolations.iterator().next().getMessage());

        for (ConstraintViolation<OrderFood> bad : constraintViolations) {
            System.out.println(bad.toString() + " " + bad.getPropertyPath() + " " + bad.getMessage());
        }
    }
    
    @Test
    public void orderDateMustBePastOrPresentTest() {
        OrderFood order = new OrderFood(
                new GregorianCalendar(2019, 9, 23).getTime(), 
                1, 
                new BigDecimal("10.00"));

        System.out.println(order.toString());

        Set<ConstraintViolation<OrderFood>> constraintViolations = validator.validate(order);

        for (ConstraintViolation<OrderFood> bad : constraintViolations) {
            System.out.println(bad.toString() + bad.getPropertyPath() + " " + bad.getMessage());
        }
        
        assertEquals(1, constraintViolations.size());

        assertEquals("must be a date in the past or in the present", constraintViolations.iterator().next().getMessage());
    }
}
