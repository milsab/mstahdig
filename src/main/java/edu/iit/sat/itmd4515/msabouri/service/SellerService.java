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
    
    public SellerService() {
        super(Seller.class);
    }

    @Override
    public List<Seller> findAll() {
        return getEntityManager().createNamedQuery("Seller.findAll", Seller.class).getResultList();
    }
    
    public Seller findByUserName(String username){
        return getEntityManager().createNamedQuery("Seller.findByUsername", Seller.class).setParameter("username", username).getSingleResult();
    }
    
}
