/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.domain;

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
public class SellerTest extends AbstractJPATest{

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
    public void persitenceSellerTest(){
        Seller seller = new Seller("Milad", "Sabouri", "M", 
                new GregorianCalendar(2017, 9, 23).getTime(), "");

        Food food = new Food("abc", "abc description", 
                Arrays.asList("Oil", "Bread", "Milk"), 
                new GregorianCalendar(2017, 9, 23).getTime());
        
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
    
    /**
     *
     */
    @Test
    public void FirstNameIsBlank(){
        Seller seller = new Seller(" ", "abc", "mail", 
                new GregorianCalendar(2017, 9, 23).getTime(), "");
        System.out.println(seller.toString());
        
        Set<ConstraintViolation<Seller>> constraintViolations = validator.validate(seller);
        assertEquals(1, constraintViolations.size());
        
        assertEquals("must not be blank", constraintViolations.iterator().next().getMessage());
        
        for(ConstraintViolation<Seller> bad : constraintViolations){
            System.out.println(bad.toString() + " " + bad.getPropertyPath() + " " + bad.getMessage());
        }
    }

    /**
     *
     */
    @Test
    public void LastNameIsBlank(){
        Seller seller = new Seller("abc", " ", "mail", 
                new GregorianCalendar(2017, 9, 23).getTime(), "");
        System.out.println(seller.toString());
        
        Set<ConstraintViolation<Seller>> constraintViolations = validator.validate(seller);
        assertEquals(1, constraintViolations.size());
        
        assertEquals("must not be blank", constraintViolations.iterator().next().getMessage());
        
        for(ConstraintViolation<Seller> bad : constraintViolations){
            System.out.println(bad.toString() + " " + bad.getPropertyPath() + " " + bad.getMessage());
        }
    }
    
}
