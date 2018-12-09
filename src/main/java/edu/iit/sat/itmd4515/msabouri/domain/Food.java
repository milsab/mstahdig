/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

/**
 *
 * @author Milad
 */
@Entity
@Table(name = "FOOD")
@NamedQueries({
    @NamedQuery(
            name = "Food.findByName",
            query = "select f from Food f where f.name = :name"),
    @NamedQuery(
            name = "Food.findAll",
            query = "select f from Food f")
})

public class Food extends AbstractIdentifiedEntity {


    @Column(name = "name", unique = true)
    @NotBlank
    private String name;
    
    @NotBlank
    private String description;
    
    @Size(min = 1, max = 20)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "recipe")
    private List<String> recipes;

    @NotNull
    @Temporal(TemporalType.DATE)
    @PastOrPresent
    @Column(name = "date_cooked")
    private Date dateCooked;
    
    @ManyToMany(mappedBy = "foods")
    private List<Offer> offers = new ArrayList<>();
    
    
//    @NotNull
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    /**
     * Each food can be listed in offer list up to three days. So, the
     * expiration can be calculated from dataCooked and it doesn't need to
     * persistence in the database
     */
    @Transient
    private Date expiration;

    /**
     *
     */
    public Food() {
    }

    /**
     *
     * @param name
     * @param description
     * @param recipe
     * @param dateCooked
     */
    public Food(String name, String description, List<String> recipe, Date dateCooked) {
        this.name = name;
        this.description = description;
        this.recipes = recipe;
        this.dateCooked = dateCooked;
    }
    
    /**
     * Helper function to manage both side of many-to-many relationship with Offer
     * @param offer
     */
    public void addOffer(Offer offer){
        if(!this.getOffers().contains(offer))
            this.getOffers().add(offer);
        if(!offer.getFoods().contains(this))
            offer.getFoods().add(this);
    }
    
    /**
     * Helper function to manage both side of many-to-one relationship with
     * Seller
     * @param seller
     */
    public void addSeller(Seller seller) {
        if (!this.getSeller().equals(seller)) {
            this.setSeller(seller);
        }
        if (!seller.getFoods().contains(this)) {
            seller.getFoods().add(this);
        }
    }


    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value of description
     *
     * @param description new value of description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the value of recipes
     *
     * @return the value of recipes
     */
    public List<String> getRecipes() {
        return recipes;
    }

    /**
     * Set the value of recipes
     *
     * @param recipes new value of recipes
     */
    public void setRecipes(List<String> recipes) {
        this.recipes = recipes;
    }

    /**
     *
     * @return
     */
    public Date getDateCooked() {
        return dateCooked;
    }

    /**
     *
     * @param dateCooked
     */
    public void setDateCooked(Date dateCooked) {
        this.dateCooked = dateCooked;
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
     * @return
     */
    public List<Offer> getOffers() {
        return offers;
    }

    /**
     *
     * @param offers
     */
    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

}
