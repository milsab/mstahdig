/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;

/**
 *
 * @author Milad
 */
@Entity
@NamedQueries({
    
    @NamedQuery(
            name = "Order.findAll",
            query = "select o from OrderFood o"),   
    @NamedQuery(
            name = "Order.findByUsername",
            query = "select o from OrderFood o where o.buyer.user.userName = :username"),
    @NamedQuery(
            name = "Order.findTotalOrders",
            query = "SELECT count(o) FROM OrderFood o ")
})
@Table(name = "ORDER_FOOD")
public class OrderFood {

    private static final Logger LOG = Logger.getLogger(OrderFood.class.getName());

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Temporal(TemporalType.DATE)
    @Column(name = "order_date")
    @PastOrPresent
    private Date orderDate;
    
    
    @Max(999)
    @Min(1)
    private Integer quantity;

    @DecimalMin("0.00")
    private BigDecimal price;
    
    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    /**
     *
     */
    public OrderFood() {
    }

    /**
     *
     * @param orderDate
     * @param quantity
     * @param price
     */
    public OrderFood(Date orderDate, Integer quantity, BigDecimal price) {
        this.orderDate = orderDate;
        this.quantity = quantity;
        this.price = price;
    }
    
    /**
     * Helper function to manage both side of many-to-one relationship with
     * Offer
     * @param offer
     */
    public void addOffer(Offer offer) {
        if (!this.getOffer().equals(offer)) {
            this.setOffer(offer);
        }
        if (!offer.getOrders().contains(this)) {
            offer.getOrders().add(this);
        }
    }
    
    /**
     * Helper function to manage both side of many-to-one relationship with
     * Buyer
     * @param buyer
     */
    public void addBuyer(Buyer buyer) {
        if (!this.getBuyer().equals(buyer)) {
            this.setBuyer(buyer);
        }
        if (!buyer.getOrders().contains(this)) {
            buyer.getOrders().add(this);
        }
    }

    /**
     * Get the value of orderId
     *
     * @return the value of orderId
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * Set the value of orderId
     *
     * @param orderId new value of orderId
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     *
     * @return
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     *
     * @param orderDate
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     *
     * @return
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderFood{" + "orderId=" + orderId + ", orderDate=" + orderDate + ", quantity=" + quantity + ", price=" + price + '}';
    }

    /**
     *
     * @return
     */
    public Offer getOffer() {
        return offer;
    }

    /**
     *
     * @param offer
     */
    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    /**
     *
     * @return
     */
    public Buyer getBuyer() {
        return buyer;
    }

    /**
     *
     * @param buyer
     */
    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
}
