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
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Milad
 */
public class OfferTest extends AbstractJPATest {

    @Before
    public void beforeEachTest() {
        super.beforeEachTest();
    }

    @After
    public void afterEachTest() {
        super.afterEachTest();
    }

    @Test
    public void persitenceOfferTest() {
        Offer offer = new Offer("Offer Title", "Offer Description",
                new GregorianCalendar(2018, 9, 23).getTime(), new BigDecimal("20.01"), 10);

        tx.begin();
        em.persist(offer);
        tx.commit();
    }

//    @Test
//    public void priceIsNegativeTest() {
//        Offer offer = new Offer("title", "description",
//                new GregorianCalendar(2018, 9, 23).getTime(),
//                new BigDecimal("-1"), 2);
//
//        System.out.println(offer.toString());
//
//        Set<ConstraintViolation<Offer>> constraintViolations = validator.validate(offer);
//        assertEquals(1, constraintViolations.size());
//
//        assertEquals("must be greater than or equal to 0", constraintViolations.iterator().next().getMessage());
//
//        for (ConstraintViolation<Offer> bad : constraintViolations) {
//            System.out.println(bad.toString() + " " + bad.getPropertyPath() + " " + bad.getMessage());
//        }
//    }
//
//    @Test
//    public void priceHas2DigitsFractionTest() {
//        Offer offer = new Offer("title", "description",
//                new GregorianCalendar(2018, 9, 23).getTime(),
//                new BigDecimal("56.988"), 2);
//
//        System.out.println(offer.toString());
//
//        Set<ConstraintViolation<Offer>> constraintViolations = validator.validate(offer);
//        assertEquals(1, constraintViolations.size());
//
//        assertEquals("numeric value out of bounds (<4 digits>.<2 digits> expected)", constraintViolations.iterator().next().getMessage());
//
//        for (ConstraintViolation<Offer> bad : constraintViolations) {
//            System.out.println(bad.toString() + " " + bad.getPropertyPath() + " " + bad.getMessage());
//        }
//    }
//
//    @Test
//    public void priceHas4DigitsIntegerTest() {
//        Offer offer = new Offer("title", "description",
//                new GregorianCalendar(2018, 9, 23).getTime(),
//                new BigDecimal("93489.02"), 2);
//
//        System.out.println(offer.toString());
//
//        Set<ConstraintViolation<Offer>> constraintViolations = validator.validate(offer);
//
//        assertEquals(1, constraintViolations.size());
//
//        assertEquals("numeric value out of bounds (<4 digits>.<2 digits> expected)", constraintViolations.iterator().next().getMessage());
//
//        for (ConstraintViolation<Offer> bad : constraintViolations) {
//            System.out.println(bad.toString() + " " + bad.getPropertyPath() + " " + bad.getMessage());
//        }
//    }
    
    @Test
    public void unitPriceAcceptMax1000Test() {
        Offer offer = new Offer("title", "description",
                new GregorianCalendar(2018, 9, 23).getTime(),
                new BigDecimal("1000.01"), 2);

        System.out.println(offer.toString());

        Set<ConstraintViolation<Offer>> constraintViolations = validator.validate(offer);

        assertEquals(1, constraintViolations.size());

        assertEquals("must be less than or equal to 1000.00", constraintViolations.iterator().next().getMessage());

        for (ConstraintViolation<Offer> bad : constraintViolations) {
            System.out.println(bad.toString() + " " + bad.getPropertyPath() + " " + bad.getMessage());
        }
    }
    
    @Test
    public void unitPriceAcceptMinZeroTest() {
        Offer offer = new Offer("title", "description",
                new GregorianCalendar(2018, 9, 23).getTime(),
                new BigDecimal("-1"), 2);

        System.out.println(offer.toString());

        Set<ConstraintViolation<Offer>> constraintViolations = validator.validate(offer);

        assertEquals(1, constraintViolations.size());

        assertEquals("must be greater than or equal to 0.00", constraintViolations.iterator().next().getMessage());

        for (ConstraintViolation<Offer> bad : constraintViolations) {
            System.out.println(bad.toString() + " " + bad.getPropertyPath() + " " + bad.getMessage());
        }
    }
}
