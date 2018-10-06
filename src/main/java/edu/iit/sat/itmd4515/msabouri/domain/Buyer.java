/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Milad
 */
@Entity
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buyerId;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String gender;
    @Temporal(TemporalType.DATE)
    private Date birthday;
    
    @OneToMany(mappedBy = "buyer")
    private List<OrderFood> orders = new ArrayList<>();

    public Buyer() {
    }

    public Buyer(String firstName, String lastName, String gender, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
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

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long BuyerId) {
        this.buyerId = BuyerId;
    }

    @Override
    public String toString() {
        return "Buyer{" + "buyerId=" + buyerId + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", birthday=" + birthday + '}';
    }

    public List<OrderFood> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderFood> orders) {
        this.orders = orders;
    }
}
