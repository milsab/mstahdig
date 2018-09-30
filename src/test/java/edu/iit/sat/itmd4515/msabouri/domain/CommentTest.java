/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.domain;

import static edu.iit.sat.itmd4515.msabouri.domain.AbstractJPATest.emf;
import java.util.GregorianCalendar;
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
}
