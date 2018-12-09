package edu.iit.sat.itmd4515.msabouri.web;

import edu.iit.sat.itmd4515.msabouri.domain.Offer;
import edu.iit.sat.itmd4515.msabouri.domain.Seller;
import edu.iit.sat.itmd4515.msabouri.service.SellerService;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Milad
 */

@Named
@RequestScoped
public class SellerController  extends AbstractController{
    private static final Logger LOG = Logger.getLogger(BuyerController.class.getName());
    
    private Seller seller;
    private Offer offer;
    
    @EJB
    private SellerService sellerSvc;

    @Inject
    private LoginController loginController;
    
    @PostConstruct
    private void postConstructor(){
        super.postConstruct();
        LOG.info("Inside SellerController postConstructor");        
        seller = sellerSvc.findByUserName(loginController.getRemoteUser());
        offer = new Offer();
    }

    /**
     *
     * @return
     */
    public Seller getSeller() {
        return seller;
    }

    /**
     *
     * @param seller
     */
    public void setSeller(Seller seller) {
        this.seller = seller;
    }
    
    /**
     *
     * @param seller
     * @return The page address that we need to redirect to it.
     */
    public String doEdit(Seller seller) {
        this.seller = seller;        
        return "/seller/editProfile.xhtml";
    }
}
