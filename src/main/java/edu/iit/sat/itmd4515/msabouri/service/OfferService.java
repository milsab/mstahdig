/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.service;

import edu.iit.sat.itmd4515.msabouri.domain.Offer;
import edu.iit.sat.itmd4515.msabouri.domain.Seller;
import edu.iit.sat.itmd4515.msabouri.web.FileUploadController;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.Part;

/**
 *
 * @author Milad
 */
@Named
@Stateless
public class OfferService extends AbstractService<Offer> {

    @EJB
    private SellerService sellerSvc;

    private Part uploadedFile;

    /**
     *
     */
    public OfferService() {
        super(Offer.class);
    }

//    @Override
//    public List<Offer> findAll(String s) {
//
//    }

    public List<Offer> findByUserName(String username) {
        return getEntityManager()
                .createNamedQuery("Offer.findByUsername", Offer.class)
                .setParameter("username", username)
                .getResultList();
    }

    public String create(Offer offer, String username) {
        Seller seller = sellerSvc.findByUserName(username);
        offer.setSeller(seller);
        String imageName = uploadedFile.getSubmittedFileName();
        offer.setImageFile(imageName);
        super.create(offer);

        String filename = Long.toString(offer.getOfferId());
        FileUploadController imageFile = new FileUploadController();
        imageFile.setUploadedFile(uploadedFile);
        imageFile.saveFile(filename);
        return "/seller/offer?faces-redirect=true";
    }
    
    public String update(Offer offer, String username){
        Seller seller = sellerSvc.findByUserName(username);
        offer.setSeller(seller);
        super.update(offer);
        return "/seller/offer.xhtml";
    }

    @Override
    public List<Offer> findAll() {
        return getEntityManager()
                .createNamedQuery("Offer.findAll", Offer.class)
                .getResultList();
    }

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

}
