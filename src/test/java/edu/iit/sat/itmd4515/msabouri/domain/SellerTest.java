/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.domain;

import static edu.iit.sat.itmd4515.msabouri.domain.AbstractJPATest.emf;
import java.util.Arrays;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Milad
 */
public class SellerTest extends AbstractJPATest{
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
        Seller seller = new Seller("Milad", "Sabouri", "M", 
                new GregorianCalendar(2018, 9, 23).getTime());

        Food food = new Food("abc", "abc description", 
                Arrays.asList("Oil", "Bread", "Milk"), 
                new GregorianCalendar(2018, 9, 23).getTime());
        
        food.setSeller(seller);
        
        tx.begin();
        em.persist(seller);
        em.persist(food);
        tx.commit();
        
        Long food_id = food.getId();
        assertNotNull("Persistence of seller should return sellerId", seller.getSellerId());
        Assert.assertEquals("Retrive seler's name correctly", "Milad", seller.getFirstName());
        
        Food f = em.find(Food.class, food_id);
        Assert.assertEquals("Seller assigned to the food correctly", "Milad", f.getSeller().getFirstName());
                
    }
    
}
