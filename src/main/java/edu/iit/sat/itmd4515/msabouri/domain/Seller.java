/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.domain;

import edu.iit.sat.itmd4515.msabouri.domain.security.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author Milad
 */
@Entity
@NamedQueries({
    
    @NamedQuery(
            name = "Seller.findAll",
            query = "select s from Seller s"),
    @NamedQuery(
            name = "Seller.findName",
            query = "select s from Seller s where s.lastName = :name"),
    @NamedQuery(
            name = "Seller.findByUsername",
            query = "select s from Seller s where s.user.userName = :username"),
    @NamedQuery(
            name = "Seller.findIdByUsername",
            query = "select s from Seller s where s.user.userName = :username"),
    @NamedQuery(
            name = "Seller.findTotalCooks",
            query = "SELECT count(s) FROM Seller s ")
})
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String gender;
    @Temporal(TemporalType.DATE)
    private Date birthday;
    
    private String email;


    @OneToMany(mappedBy = "seller")
    private List<Food> foods = new ArrayList<>();
    
    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;
    
    @OneToMany(mappedBy = "seller")
    private List<Offer> offers = new ArrayList<>();

    /**
     *
     */
    public Seller() {
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param gender
     * @param birthday
     * @param email
     */
    public Seller(String firstName, String lastName, String gender, Date birthday, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
    }

    /**
     * Helper function to manage both side of one-to-many relationship with
     * Food
     * @param food
     */
    public void addFood(Food food) {
        if (!this.getFoods().contains(food)) {
            this.getFoods().add(food);
        }
        if (!food.getSeller().equals(this)) {
            food.setSeller(this);
        }
    }
    
    /**
     * Get the value of firstName
     *
     * @return the value of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the value of firstName
     *
     * @param firstName new value of firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the value of lastName
     *
     * @return the value of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the value of lastName
     *
     * @param lastName new value of lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the value of gender
     *
     * @return the value of gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set the value of gender
     *
     * @param gender new value of gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Get the value of birthday
     *
     * @return the value of birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Set the value of birthday
     *
     * @param birthday new value of birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     *
     * @return
     */
    public Long getSellerId() {
        return sellerId;
    }

    /**
     *
     * @param sellerId
     */
    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "Seller{" + "sellerId=" + sellerId + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", birthday=" + birthday + '}';
    }

    /**
     *
     * @return
     */
    public List<Food> getFoods() {
        return foods;
    }

    /**
     *
     * @param foods
     */
    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
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

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
