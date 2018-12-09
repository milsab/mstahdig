package edu.iit.sat.itmd4515.msabouri.service;

import edu.iit.sat.itmd4515.msabouri.domain.Offer;
import edu.iit.sat.itmd4515.msabouri.domain.Seller;
import edu.iit.sat.itmd4515.msabouri.web.FileUploadController;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.validation.constraints.NotBlank;

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

    private List<Offer> searchResults;

    /**
     *
     */
    public OfferService() {
        super(Offer.class);
    }

    /**
     *
     * @param username
     * @return
     */
    public List<Offer> findByUserName(String username) {
        return getEntityManager()
                .createNamedQuery("Offer.findByUsername", Offer.class)
                .setParameter("username", username)
                .getResultList();
    }

    /**
     *
     * @param offer
     * @param username
     * @return the page address that we need to redirect to it.
     */
    public String create(Offer offer, String username) {
        Seller seller = sellerSvc.findByUserName(username);
        offer.setSeller(seller);
        if (uploadedFile != null) {
            String imageName = uploadedFile.getSubmittedFileName();
            offer.setImageFile(imageName);
        }
        super.create(offer);

        if (uploadedFile != null) {
            String filename = Long.toString(offer.getOfferId());
            FileUploadController imageFile = new FileUploadController();
            imageFile.setUploadedFile(uploadedFile);
            imageFile.saveFile(filename);
        }

        return "/seller/offer?faces-redirect=true";
    }

    /**
     *
     * @param offer
     * @param username
     * @return the page address that we need to redirect to it.
     */
    public String update(Offer offer, String username) {
        Seller seller = sellerSvc.findByUserName(username);
        offer.setSeller(seller);
        super.update(offer);
        return "/seller/offer.xhtml";
    }

    /**
     *
     * @return
     */
    @Override
    public List<Offer> findAll() {
        return getEntityManager()
                .createNamedQuery("Offer.findAll", Offer.class)
                .getResultList();
    }

    /**
     *
     * @param keyword
     * @return the page address that we need to redirect to it.
     */
    public String findAllBySearch(String keyword) {

        searchResults = getEntityManager()
                .createNamedQuery("Offer.findBySearch", Offer.class)
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();
        return "/buyer/searchResults";

    }
    
    public Long findTotalOffers(){
        return  (Long) getEntityManager().createNamedQuery("Offer.findTotalOffers").getSingleResult();        
    }

    /**
     *
     * @return List of all available offers
     */
    public List<Offer> findAllAvailables() {
        return getEntityManager()
                .createNamedQuery("Offer.findAllAvailables", Offer.class)
                .getResultList();
    }

    /**
     *
     * @return
     */
    public Part getUploadedFile() {
        return uploadedFile;
    }

    /**
     *
     * @param uploadedFile
     */
    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    /**
     *
     * @return
     */
    public List<Offer> getSearchResults() {
        return searchResults;
    }

    /**
     *
     * @param searchResults
     */
    public void setSearchResults(List<Offer> searchResults) {
        this.searchResults = searchResults;
    }

}
