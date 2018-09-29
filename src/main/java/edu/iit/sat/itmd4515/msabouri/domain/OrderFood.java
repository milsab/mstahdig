/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.domain;

import java.util.Date;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Milad
 */
@Entity
@Table(name = "ORDER_FOOD")
public class OrderFood {

    private static final Logger LOG = Logger.getLogger(OrderFood.class.getName());

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Temporal(TemporalType.DATE)
    @Column(name = "order_date")
    private Date orderDate;
    private Integer quantity;
    private Integer price;

    public OrderFood() {
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderFood{" + "orderId=" + orderId + ", orderDate=" + orderDate + ", quantity=" + quantity + ", price=" + price + '}';
    }
}
