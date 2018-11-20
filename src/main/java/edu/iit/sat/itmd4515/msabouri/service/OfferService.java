/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.service;

import edu.iit.sat.itmd4515.msabouri.domain.Offer;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Milad
 */
@Named
@Stateless
public class OfferService {

    @PersistenceContext(unitName = "itmd4515PU")
    private EntityManager em;

    /**
     *
     */
    public OfferService() {
    }

    /**
     *
     * @param offer the offer that you want to create in persistence unit
     */
    public void create(Offer offer) {
        em.persist(offer);
    }

    /**
     *
     * @param offer the offer that you want to update in persistence unit
     */
    public void update(Offer offer) {
        em.merge(offer);
    }

    /**
     *
     * @param offer     the offer that you want to remove
     */
    public void remove(Offer offer) {
        em.remove(em.merge(offer));
    }

    /**
     *
     * @param id    The id for the food to find the food
     * @return
     */
    public Offer findById(Long id) {
        return em.find(Offer.class, id);
    }

    /**
     *  Find All Offers
     *
     * @return  Al Offers
     */
    public List<Offer> findAll() {
        return em.createNamedQuery("Offer.findAll", Offer.class).getResultList();
    }

}
