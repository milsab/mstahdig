package edu.iit.sat.itmd4515.msabouri.domain;


import java.math.BigDecimal;
import java.util.ArrayList;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

/**
 *
 * @author Milad
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name = "Offer.findAll",
            query = "SELECT o FROM Offer o"),
    @NamedQuery(
            name = "Offer.findAllAvailables",
            query = "SELECT o FROM Offer o WHERE o.quantity > 0"),
    @NamedQuery(
            name = "Offer.findByUsername",
            query = "SELECT o FROM Offer o WHERE o.seller.user.userName = :username"),
    @NamedQuery(
            name = "Offer.findBySearch",
            query = "SELECT o FROM Offer o WHERE o.title LIKE :keyword AND o.quantity > 0"),
    @NamedQuery(
            name = "Offer.findTotalOffers",
            query = "SELECT count(o) FROM Offer o ")    
})
public class Offer {

    private static final Logger LOG = Logger.getLogger(Offer.class.getName());

    /**
     *
     * @return
     */
    public static Logger getLOG() {
        return LOG;
    }

    // <editor-fold desc="Attributes">
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id")
    private Long offerId;

    @NotBlank(message = "You must enter the Title")
    private String title;
    @NotBlank(message = "You must enter the Description")
    private String description;

    @Temporal(TemporalType.DATE)
    @PastOrPresent
    private Date createdDate;

    @NotNull(message = "You must enter the Price")
    @DecimalMax("1000.00")
    @DecimalMin("0.00")
    private BigDecimal unitPrice;

    @NotNull(message = "You must enter the Quantity")
    @Min(0)
    private Integer quantity;

    @OneToMany(mappedBy = "offer")
    private List<OrderFood> orders = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "food_offer", joinColumns = @JoinColumn(name = "offer_id"), inverseJoinColumns = @JoinColumn(name = "food_id"))
    private List<Food> foods = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
    
    
    private String recipe;
    
    
    @Column(name = "image_file")
    private String imageFile;
    
    @Transient
    private Integer qty;

    
    // </editor-fold>

    /**
     *
     */
    public Offer() {
        this.createdDate = new Date();
        this.qty = new Integer(0);
    }

    /**
     *
     * @param title
     * @param description
     * @param createdDate
     * @param unitPrice
     * @param quantity
     * @param recipe
     * @param imageFile
     */
    public Offer(String title, String description, Date createdDate, BigDecimal unitPrice, Integer quantity, String recipe, String imageFile) {
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;

        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.recipe = recipe;
        this.imageFile = imageFile;

        if (this.createdDate == null) {            
            this.createdDate = new Date();
        } else{
            this.createdDate = createdDate;
        }

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
        if (!order.getOffer().equals(this)) {
            order.setOffer(this);
        }
    }

    /**
     * Helper function to manage both side of Many-to-Many relationship with
     * Food
     * @param food
     */
    public void addFood(Food food) {
        if (!this.getFoods().contains(food)) {
            this.getFoods().add(food);
        }
        if (!food.getOffers().contains(this)) {
            food.getOffers().add(this);
        }
    }

    // <editor-fold desc="Setter/Getters">
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

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     *
     * @param createdDate
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     *
     * @return
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     *
     * @param unitPrice
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
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

    @Override
    public String toString() {
        return "Offer{" + "offerId=" + offerId + ", title=" + title + ", description=" + description + ", createdDate=" + createdDate + ", unitPrice=" + unitPrice + ", quantity=" + quantity + '}';
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

    // <editor-fold>

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
    public String getRecipe() {
        return recipe;
    }

    /**
     *
     * @param recipe
     */
    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    /**
     *
     * @return
     */
    public String getImageFile() {
        return imageFile;
    }

    /**
     *
     * @param imageFile
     */
    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    /**
     *
     * @return
     */
    public Integer getQty() {
        return qty;
    }

    /**
     *
     * @param qty
     */
    public void setQty(Integer qty) {
        this.qty = qty;
    }
}