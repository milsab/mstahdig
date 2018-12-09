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
            name = "Buyer.findAll",
            query = "select b from Buyer b"),
    @NamedQuery(
            name = "Buyer.findName",
            query = "select b from Buyer b where b.lastName = :name"),
    @NamedQuery(
            name = "Buyer.findByUsername",
            query = "select b from Buyer b where b.user.userName = :username"),
    @NamedQuery(
            name = "Buyer.findTotalCustomers",
            query = "SELECT count(b) FROM Buyer b ")
})
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buyerId;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String gender;
    @Temporal(TemporalType.DATE)
    private Date birthday;
    
    private String email;
    

    @OneToMany(mappedBy = "buyer")
    private List<OrderFood> orders = new ArrayList<>();
    
    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    /**
     *
     */
    public Buyer() {
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param gender
     * @param birthday
     * @param email
     */
    public Buyer(String firstName, String lastName, String gender, Date birthday, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
    }
    
    /**
     * Helper function to manage both side of one-to-many relationship with
     * OrderFood
     * @param order
     */
    public void addOrder(OrderFood order) {
        if (!this.getOrders().contains(order)) {
            this.getOrders().add(order);
        }
        if (!order.getBuyer().equals(this)) {
            order.setBuyer(this);
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
    public Long getBuyerId() {
        return buyerId;
    }

    /**
     *
     * @param buyerId
     */
    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    @Override
    public String toString() {
        return "Buyer{" + "buyerId=" + buyerId + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", birthday=" + birthday + '}';
    }

    /**
     *
     * @return
     */
    public List<OrderFood> getOrders() {
        return orders;
    }

    /**
     *
     * @param orders
     */
    public void setOrders(List<OrderFood> orders) {
        this.orders = orders;
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
