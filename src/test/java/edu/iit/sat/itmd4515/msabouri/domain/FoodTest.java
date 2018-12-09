/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.domain;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.persistence.RollbackException;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Milad
 */
public class FoodTest extends AbstractJPATest {

    /**
     *
     */
    @Before
    @Override
    public void beforeEachTest() {
        super.beforeEachTest();
        Food seed = new Food(
                "SEED",
                "SEED",
                Arrays.asList("SEED", "SEED", "SEED"),
                new GregorianCalendar(2018, 7, 23).getTime());

        tx.begin();
        em.persist(seed);
        tx.commit();
    }

    /**
     *
     */
    @After
    @Override
    public void afterEachTest() {
        Food seed = em
                .createNamedQuery("Food.findByName", Food.class)
                .setParameter("name", "SEED")
                .getSingleResult();

        tx.begin();
        em.remove(seed);
        tx.commit();
        super.afterEachTest();
    }
    
    /**
     *
     */
    @Test
    public void verifySeedDataSet() {
        List<Food> seeds = em
                .createNamedQuery("Food.findByName", Food.class)
                .setParameter("name", "SEED")
                .getResultList();

        assertEquals(1, seeds.size());
        assertEquals("SEED", seeds.get(0).getName());
    }

    /**
     * SUNNY DAY test for persist FOOD
     */
    @Test
    public void persistNewFoodAndExpectSuccess() {
        Food food = new Food(
                "Kooko",
                "Vegeterian",
                Arrays.asList("Spinach", "Egg", "Olive Oil"),
                new GregorianCalendar(2018, 7, 23).getTime());

        tx.begin();
        assertNull("ID should be NULL until after em.persist()", food.getId());
        em.persist(food);
        assertNull("ID should be NULL until after em.persist() and before before tx.commit()", food.getId());
        tx.commit();
        assertNotNull("ID should NOT be NULL after tx.commit()", food.getId());
        assertTrue("ID should be greater than 0", food.getId() > 0L);
    }

    /**
     * RAINY DAY test for persist FOOD
     */
    @Test(expected = RollbackException.class)
    public void persistNewFoodAndExpectFailure() {
        Food food = new Food(
                "SEED",
                "SEED",
                Arrays.asList("SEED", "SEED", "SEED"),
                new GregorianCalendar(2018, 7, 23).getTime());

        tx.begin();
        em.persist(food);
        tx.commit();
    }

    /**
     * SUNNY DAY test for remove FOOD
     */
    @Test
    public void removeFoodAndExpectSuccess() {
        Food seed = em
                .createNamedQuery("Food.findByName", Food.class)
                .setParameter("name", "SEED")
                .getSingleResult();

        tx.begin();
        em.remove(seed);
        tx.commit();

        List<Food> seeds = em
                .createNamedQuery("Food.findByName", Food.class)
                .setParameter("name", "SEED")
                .getResultList();

        assertEquals(0, seeds.size());

        em = emf.createEntityManager();
        tx = em.getTransaction();

        Food seed2 = new Food(
                "SEED",
                "SEED",
                Arrays.asList("SEED", "SEED", "SEED"),
                new GregorianCalendar(2018, 7, 23).getTime());

        tx.begin();
        em.persist(seed2);
        tx.commit();

    }

    /**
     * SUNNY DAY test for read FOOD
     */
    @Test
    public void findSeedFoodAndExpectSuccess() {
        Food seed = em
                .createNamedQuery("Food.findByName", Food.class)
                .setParameter("name", "SEED")
                .getSingleResult();
        System.out.print(seed.getDescription());
        assertEquals("SEED", seed.getName());
    }

    /**
     * RAINY DAY test for read FOOD
     */
    @Test
    public void findSeedFoodAndExpectFailure() {
        List<Food> seeds = em
                .createNamedQuery("Food.findByName", Food.class)
                .setParameter("name", "SEED2")
                .getResultList();
        assertEquals(0, seeds.size());
    }

    /**
     * SUNNY DAY test for update FOOD
     */
    @Test
    public void updateFoodAndExpectSuccess() {
//        Food seed = em
//                .createNamedQuery("Food.findByName", Food.class)
//                .setParameter("name", "SEED")
//                .getSingleResult();
//        seed.setDescription("TestDescription");
//        assertEquals("TestDescription", seed.getDescription());

        Food food = new Food("Milk", "Vegeterian", 
                Arrays.asList("Spinach", "Egg", "Olive Oil"), 
                new GregorianCalendar(2018, 7, 23).getTime());
        
        tx.begin();
        em.persist(food);
        tx.commit();
        
        assertTrue(em.contains(food));
        
        em.clear();
        assertFalse(em.contains(food));
        
        food.setName("Bread");
        tx.begin();
        em.merge(food);
        tx.commit();
        
        em.clear();
        assertFalse(em.contains(food));
        
        food = em.find(Food.class, food.getId());
        assertEquals(food.getName(), "Bread");
        assertTrue(em.contains(food));
        
    }
    
    /**
     *
     */
    @Test
    public void nameIsBlank(){
        Food food  = new Food(" ", "description", 
                Arrays.asList("Spinach", "Egg", "Olive Oil"), 
                new GregorianCalendar(2018, 7, 23).getTime());
        
        System.out.println(food.toString());
        
        Set<ConstraintViolation<Food>> constraintViolations = validator.validate(food);
        assertEquals(1, constraintViolations.size());
        
        assertEquals("must not be blank", constraintViolations.iterator().next().getMessage());
        
        constraintViolations.forEach((bad) -> {
            System.out.println(bad.toString() + " " + bad.getPropertyPath() + " " + bad.getMessage());
        });
    }
    
    /**
     *
     */
    @Test
    public void descriptionIsBlank(){
        Food food  = new Food("title", " ", 
                Arrays.asList("Spinach", "Egg", "Olive Oil"), 
                new GregorianCalendar(2018, 7, 23).getTime());
        
        System.out.println(food.toString());
        
        Set<ConstraintViolation<Food>> constraintViolations = validator.validate(food);
        assertEquals(1, constraintViolations.size());
        
        assertEquals("must not be blank", constraintViolations.iterator().next().getMessage());
        
        constraintViolations.forEach((bad) -> {
            System.out.println(bad.toString() + " " + bad.getPropertyPath() + " " + bad.getMessage());
        });
    }
    
    /**
     *
     */
    @Test
    public void DateIsNull(){
        Food food  = new Food("title", "description", 
                Arrays.asList("Spinach", "Egg", "Olive Oil"), 
                null);
        
        System.out.println(food.toString());
        
        Set<ConstraintViolation<Food>> constraintViolations = validator.validate(food);
        assertEquals(1, constraintViolations.size());
        
        assertEquals("must not be null", constraintViolations.iterator().next().getMessage());
        
        constraintViolations.forEach((bad) -> {
            System.out.println(bad.toString() + " " + bad.getPropertyPath() + " " + bad.getMessage());
        });
    }
    
    /**
     *
     */
    @Test
    public void recipeHasAtLeastOneElement(){
        Food food  = new Food("title", "description", 
                null, 
                new GregorianCalendar(2018, 7, 23).getTime());
        
        System.out.println(food.toString());
        
        Set<ConstraintViolation<Food>> constraintViolations = validator.validate(food);
        assertEquals(1, constraintViolations.size());
        
        assertEquals("size must be between 1 and 20", constraintViolations.iterator().next().getMessage());
        
        constraintViolations.forEach((bad) -> {
            System.out.println(bad.toString() + " " + bad.getPropertyPath() + " " + bad.getMessage());
        });
    }
    
    /**
     *
     */
    @Test
    public void dateCookedMustBePastOrPresent(){
        Food food  = new Food("title", "description", 
                Arrays.asList("Spinach", "Egg", "Olive Oil"), 
                new GregorianCalendar(2019, 7, 23).getTime());
        
        System.out.println(food.toString());
        
        Set<ConstraintViolation<Food>> constraintViolations = validator.validate(food);
                
        constraintViolations.forEach((bad) -> {
            System.out.println(bad.toString() + " " + bad.getPropertyPath() + " " + bad.getMessage());
        });
        
        assertEquals(1, constraintViolations.size());
        
        assertEquals("must be a date in the past or in the present", constraintViolations.iterator().next().getMessage());
    }
    
    
}
