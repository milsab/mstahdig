/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.service;

import edu.iit.sat.itmd4515.msabouri.domain.Seller;
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
public class SellerService extends AbstractService<Seller> {
    
    @PersistenceContext(unitName = "itmd4515PU")
    private EntityManager em;
    
    /**
     *
     */
    public SellerService() {
        super(Seller.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Seller> findAll() {
        return getEntityManager().createNamedQuery("Seller.findAll", Seller.class).getResultList();
    }
    
    /**
     *
     * @param username
     * @return
     */
    public Seller findByUserName(String username){
        return getEntityManager().createNamedQuery("Seller.findByUsername", Seller.class).setParameter("username", username).getSingleResult();
    }
    
    /**
     *
     * @param username
     * @return
     */
    public Seller findIdByUserName(String username){
        return getEntityManager().createNamedQuery("Seller.findIdByUsername", Seller.class).setParameter("username", username).getSingleResult();
    }
    
    /**
     *
     * @return
     */
    public Long findTotalCooks(){
        return  (Long) getEntityManager().createNamedQuery("Seller.findTotalCooks").getSingleResult();        
    }
    
    /**
     *
     * @param seller
     * @return The page address that we need to redirect to it.
     */
    public String executeEdit(Seller seller){
        super.update(seller);
        return "/seller/profile.xhtml?faces-redirect=true";
    }
}