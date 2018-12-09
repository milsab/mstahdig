/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.domain;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Milad
 */
public class OfferTest extends AbstractJPATest {

    /**
     *
     */
    @Before
    public void beforeEachTest() {
        super.beforeEachTest();
    }

    /**
     *
     */
    @After
    public void afterEachTest() {
        super.afterEachTest();
    }

    /**
     *
     */
    @Test
    public void persitenceOfferTest() {
        Offer offer = new Offer("Offer Title", "Offer Description",
                new GregorianCalendar(2017, 9, 23).getTime(), 
                new BigDecimal("20.01"), 10, "", "");

        tx.begin();
        em.persist(offer);
        tx.commit();
    }
    
    /**
     *
     */
    @Test
    public void unitPriceAcceptMax1000Test() {
        
        Offer offer = new Offer("title", "description",
                new GregorianCalendar(2018, 8, 23).getTime(),
                new BigDecimal("1000.01"), 2, "", "");

        System.out.println(offer.toString());
 
        Set<ConstraintViolation<Offer>> constraintViolations = validator.validate(offer);

        for (ConstraintViolation<Offer> bad : constraintViolations) {
            System.out.println(bad.toString() + bad.getPropertyPath() + " " + bad.getMessage());
        }
        
        assertEquals(1, constraintViolations.size());

        assertEquals("must be less than or equal to 1000.00", constraintViolations.iterator().next().getMessage());
    }
    
    /**
     *
     */
    @Test
    public void unitPriceAcceptMinZeroTest() {
        Offer offer = new Offer("title", "description",
                new GregorianCalendar(2018, 8, 23).getTime(),
                new BigDecimal("-1"), 2, "", "");

        System.out.println(offer.toString());

        Set<ConstraintViolation<Offer>> constraintViolations = validator.validate(offer);

        for (ConstraintViolation<Offer> bad : constraintViolations) {
            System.out.println(bad.toString() + " &&&&&& " + bad.getPropertyPath() + " " + bad.getMessage());
        }
        
        assertEquals(1, constraintViolations.size());

        assertEquals("must be greater than or equal to 0.00", constraintViolations.iterator().next().getMessage());
    }
    
    /**
     *
     */
    @Test
    public void createdDateMustBePastOrPresentTest() {
        Offer offer = new Offer("title", "description",
                new GregorianCalendar(2019, 9, 8).getTime(),
                new BigDecimal("10.00"), 2, "", "");

        System.out.println(offer.toString());

        Set<ConstraintViolation<Offer>> constraintViolations = validator.validate(offer);

        for (ConstraintViolation<Offer> bad : constraintViolations) {
            System.out.println(bad.toString() + bad.getPropertyPath() + " " + bad.getMessage());
        }
        
        assertEquals(1, constraintViolations.size());

        assertEquals("must be a date in the past or in the present", constraintViolations.iterator().next().getMessage());
    }
}
