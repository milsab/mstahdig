/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Milad
 */
@Entity
public class Offer {

    private static final Logger LOG = Logger.getLogger(Offer.class.getName());

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerId;
    private String title;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    private Integer unitPrice;
    private Integer quantity;
//    @OneToMany
//    private List<OrderFood> orders = new ArrayList<>();
    

    public Offer() {
    }



    /**
     * Get the value of offerId
     *
     * @return the value of offerId
     */
    public Long getOfferId() {
        return offerId;
    }

    /**
     * Set the value of offerId
     *
     * @param offerId new value of offerId
     */
    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public Integer getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Offer{" + "offerId=" + offerId + ", title=" + title + ", description=" + description + ", createdDate=" + createdDate + ", unitPrice=" + unitPrice + ", quantity=" + quantity + '}';
    }
//    public List<OrderFood> getOrders() {
//        return orders;
//    }
//    public void setOrders(List<OrderFood> orders) {
//        this.orders = orders;
//    }

}
