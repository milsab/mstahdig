/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 *
 * @author Milad
 */
@Entity
public class Offer {

    private static final Logger LOG = Logger.getLogger(Offer.class.getName());

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id")
    private Long offerId;
    private String title;
    private String description;
    
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Digits(integer = 4, fraction = 2)
    @Min(0)
    @Max(9999)
    private BigDecimal unitPrice;
    private Integer quantity;
    
    @OneToMany(mappedBy = "offer")
    private List<OrderFood> orders = new ArrayList<>();
    
    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    public Offer() {
    }

    public Offer(String title, String description, Date createdDate, BigDecimal unitPrice, Integer quantity) {
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        
        this.unitPrice = unitPrice;
        this.quantity = quantity;
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
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(BigDecimal unitPrice) {
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
    public List<OrderFood> getOrders() {
        return orders;
    }
    public void setOrders(List<OrderFood> orders) {
        this.orders = orders;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

}
