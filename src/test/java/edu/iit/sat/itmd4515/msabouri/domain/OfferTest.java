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
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Milad
 */
public class OfferTest extends AbstractJPATest{
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
    public void persitenceOfferTest(){
        Offer offer = new Offer("Offer Title", "Offer Description", 
                new GregorianCalendar(2018, 9, 23).getTime(), new BigDecimal("20.01"), 10);
        
        tx.begin();
        em.persist(offer);
        tx.commit();
    }
}
