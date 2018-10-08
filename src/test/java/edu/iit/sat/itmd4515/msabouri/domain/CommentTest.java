/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.domain;

import static edu.iit.sat.itmd4515.msabouri.domain.AbstractJPATest.emf;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Milad
 */
public class CommentTest extends AbstractJPATest{
    @Before
    public void beforeEachTest() {
        super.beforeEachTest();
    }

    @After
    public void afterEachTest() {
        super.afterEachTest();
    }
    
    @Test
    public void persitenceFirstCommnetTest(){
        Comment comment = new Comment("First Comment", 
                "Description of first comment", 
                new GregorianCalendar(2018, 9, 23).getTime());
        
        tx.begin();
        em.persist(comment);
        tx.commit();
        
        assertTrue("First comment must not have a child", comment.getChildren().size() == 0);
        assertNull("First comment must not have a parent", comment.getParent());
        
        Comment childComment = new Comment("Child Comment", 
                "Child Description", 
                new GregorianCalendar(2018, 9, 23).getTime());
        
        comment.getChildren().add(childComment);
        childComment.setParent(comment);
        
        tx.begin();
        em.persist(childComment);
        tx.commit();
        
        
        assertTrue("Commnet can have a child", comment.getChildren().size() == 1);
        assertNotNull("Child comment must have a parent", childComment.getParent());
        
    }
    
    @Test
    public void textIsBlank(){
        Comment comment = new Comment("Title", " ", 
                new GregorianCalendar(2018, 9, 23).getTime());
        System.out.println(comment.toString());
        
        Set<ConstraintViolation<Comment>> constraintViolations = validator.validate(comment);
        assertEquals(1, constraintViolations.size());
        
        assertEquals("must not be blank", constraintViolations.iterator().next().getMessage());
        
        for(ConstraintViolation<Comment> bad : constraintViolations){
            System.out.println(bad.toString() + " " + bad.getPropertyPath() + " " + bad.getMessage());
        }
    }
}
