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
 * @param <T>
 */
public abstract class AbstractService<T> {

    @PersistenceContext(unitName = "itmd4515PU")
    private EntityManager em;

    private final Class<T> entityClass;

    /**
     *
     * @param entityClass
     */
    public AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     *
     * @return EntityManager
     */
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     *
     * @param entity
     */
    public void create(T entity) {
        em.persist(entity);
        em.flush();
    }

    /**
     *
     * @param entity
     */
    public void update(T entity) {
        em.merge(entity);
    }

    /**
     *
     * @param entity
     * @return if successful returns NULL otherwise returns error message
     */
    public String remove(T entity) {
        try {
            em.remove(em.merge(entity));
            em.flush();
            return null;
        } catch (Exception exception){
            return exception.getMessage();
        }

    }

    /**
     *
     * @param id
     * @return
     */
    public T findById(Object id) {
        return em.find(entityClass, id);
    }

    /**
     *
     * @return
     */
    public abstract List<T> findAll();
}
