/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

/**
 *
 * @author Milad
 */
@Entity
@Table(name = "FOOD")
@NamedQueries({
    @NamedQuery(
            name = "Food.findByName",
            query = "select f from Food f where f.name = :name")
})

public class Food extends AbstractIdentifiedEntity {


    @Column(name = "name", unique = true)
    @NotNull
    private String name;
    private String description;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "recipe")
    private List<String> recipe;

    @Temporal(TemporalType.DATE)
//    @PastOrPresent
    @Column(name = "date_cooked")
    private Date dateCooked;
    
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

    public Food() {
    }

    public Food(String name, String description, List<String> recipe, Date dateCooked) {
        this.name = name;
        this.description = description;
        this.recipe = recipe;
        this.dateCooked = dateCooked;
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
     * Get the value of recipe
     *
     * @return the value of recipe
     */
    public List<String> getRecipe() {
        return recipe;
    }

    /**
     * Set the value of recipe
     *
     * @param recipe new value of recipe
     */
    public void setRecipe(List<String> recipe) {
        this.recipe = recipe;
    }

    public Date getDateCooked() {
        return dateCooked;
    }

    public void setDateCooked(Date dateCooked) {
        this.dateCooked = dateCooked;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

}
