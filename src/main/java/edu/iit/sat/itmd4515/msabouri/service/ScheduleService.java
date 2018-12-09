package edu.iit.sat.itmd4515.msabouri.service;

import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 *
 * @author Milad
 */
@Stateless
public class ScheduleService {

    private static final Logger LOG = Logger.getLogger(ScheduleService.class.getName());

    private Long totalOffer;
    private Long totalOrders;
    private Long totalCooks;
    private Long totalCustomers;

    @EJB
    private OfferService offerSvc;

    @EJB
    private OrderService orderSvc;

    @EJB
    private SellerService sellerSvc;

    @EJB
    private BuyerService buyerSvc;

    /**
     * Send the total number of orders, offers, cooks, and customers
     * to the admin email every midnights at 00:15:01 a.m.
     */
    @Schedule(second = "01", minute = "15", hour = "00")
    public void sendEmail() {
        EmailService emailSvc = new EmailService(
                "milad.sabouri@gmail.com",
                "Total Offers: " + getTotalOffers()
                + "\nTotal Orders: " + getTotalOrders()
                + "\nTotal Cooks: " + getTotalCooks()
                + "\nTotal Customers: " + getTotalCustomers(),
                "Daily Report For TAHDIG service");
        Thread th = new Thread(emailSvc);
        LOG.info(new Date() + "Starting a new thread to send the daily report email to admin...");
        th.start();
    }

    /**
     *
     * @return
     */
    public String getTotalOffers() {
        totalOffer = offerSvc.findTotalOffers();
        return Long.toString(totalOffer);
    }

    /**
     *
     * @return
     */
    public String getTotalOrders() {
        totalOrders = orderSvc.findTotalOrders();
        return Long.toString(totalOrders);
    }

    /**
     *
     * @return
     */
    public String getTotalCooks() {
        totalCooks = sellerSvc.findTotalCooks();
        return Long.toString(totalCooks);
    }

    /**
     *
     * @return
     */
    public String getTotalCustomers() {
        totalCustomers = buyerSvc.findTotalCustomers();
        return Long.toString(totalCustomers);
    }

}
