/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.service;

import edu.iit.sat.itmd4515.msabouri.domain.Buyer;
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
public class BuyerService extends AbstractService<Buyer> {

    @PersistenceContext(unitName = "itmd4515PU")
    private EntityManager em;
    
    /**
     *
     */
    public BuyerService() {
        super(Buyer.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Buyer> findAll() {
        return getEntityManager().createNamedQuery("Buyer.findAll", Buyer.class).getResultList();
    }
    
    /**
     *
     * @param username
     * @return
     */
    public Buyer findByUserName(String username){
        return getEntityManager().createNamedQuery("Buyer.findByUsername", Buyer.class).setParameter("username", username).getSingleResult();
    }
    
    /**
     *
     * @return
     */
    public Long findTotalCustomers(){
        return  (Long) getEntityManager().createNamedQuery("Buyer.findTotalCustomers").getSingleResult();        
    }
    
    
    /**
     *
     * @param buyer
     * @return the related JSF page to redirect to it
     */
    public String executeEdit(Buyer buyer){
        super.update(buyer);
        return "/buyer/profile.xhtml?faces-redirect=true";
    }
    
}
