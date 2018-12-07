/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.service;

import edu.iit.sat.itmd4515.msabouri.domain.Offer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Milad
 */
public abstract class AbstractService<T> {
    
    @PersistenceContext(unitName = "itmd4515PU")
    private EntityManager em;
    
    private final Class<T> entityClass;

    public AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public void create(T entity) {
        em.persist(entity);
        em.flush();
    }
    
    public void update(T entity){
        em.merge(entity);
    }
    
    public void remove(T entity){
        em.remove(em.merge(entity));
    }
    
    public T findById(Object id){
        return em.find(entityClass, id);
    }
    
    public abstract List<T> findAll();
}
