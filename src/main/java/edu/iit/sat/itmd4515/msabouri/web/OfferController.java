package edu.iit.sat.itmd4515.msabouri.web;

import edu.iit.sat.itmd4515.msabouri.domain.Offer;
import edu.iit.sat.itmd4515.msabouri.domain.Seller;
import edu.iit.sat.itmd4515.msabouri.service.OfferService;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Milad
 */
@Named
@RequestScoped
public class OfferController {

    private static final Logger LOG = Logger.getLogger(OfferController.class.getName());

    private Offer offer;

    private Seller seller;

    @EJB
    private OfferService offerSvc;

    @PostConstruct
    private void postConstruct() {
        LOG.info("Inside OfferController postConstructor");

        offer = new Offer();

    }

    /**
     *
     * @return The page address that we need to redirect to it.
     */
    public String executeSaveOffer() {
        LOG.info("Inside OfferController executeSaveOffer");
        this.offer = offer;
        this.offer.setSeller(seller);
        offerSvc.update(this.offer);
        return "/seller/offer.xhtml";
    }

    /**
     *
     * @param offer
     * @return The page address that we need to redirect to it.
     */
    public String doEdit(Offer offer) {
        this.offer = offer;
        this.seller = offer.getSeller();
        return "/seller/editOffer.xhtml";
    }

    /**
     *
     * @param offer
     * @return The page address that we need to redirect to it.
     */
    public String doRemove(Offer offer) {
        this.offer = offer;
        if (offerSvc.remove(offer) == null) {
            return "/seller/offer.xhtml?faces-redirect=true";
        } else {
            return "/seller/deleteOffer.xhtml?faces-redirect=true";
        }
    }

    /**
     *
     * @param offer
     * @return The page address that we need to redirect to it.
     */
    public String doUpdate(Offer offer) {
        return "/seller/offer.xhtml";
    }

    /**
     * Get the value of offer
     *
     * @return the value of offer
     */
    public Offer getOffer() {
        return offer;
    }

    /**
     * Set the value of offer
     *
     * @param offer new value of offer
     */
    public void setOffer(Offer offer) {
        this.offer = offer;
    }

}
