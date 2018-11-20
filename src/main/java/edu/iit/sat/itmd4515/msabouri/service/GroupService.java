/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.service;

import edu.iit.sat.itmd4515.msabouri.domain.security.Group;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Milad
 */
@Stateless
public class GroupService extends AbstractService<Group> {
    
    @PersistenceContext(unitName = "itmd4515PU")
    private EntityManager em;
    
    public GroupService() {
        super(Group.class);
    }

    @Override
    public List<Group> findAll() {
        return getEntityManager().createNamedQuery("Group.findAll", Group.class).getResultList();
    }  
}
