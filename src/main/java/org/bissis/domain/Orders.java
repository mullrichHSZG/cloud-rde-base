package org.bissis.domain;

import org.bissis.enums.OrderStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Markus Ullrich
 */
@Entity
public class Orders extends AbstractDomainClass {

    @OneToOne
    private Customer customer;

    @Embedded
    private Address shippingAddress;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders", orphanRemoval = true)
    private List<OrderLine> orderLines = new ArrayList<>();

    private Date dateShipped;

    private OrderStatus orderStatus;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Date getDateShipped() {
        return dateShipped;
    }

    public void setDateShipped(Date dateShipped) {
        this.dateShipped = dateShipped;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public void addOrderLine(OrderLine orderLine) {
        this.orderLines.add(orderLine);
        orderLine.setOrders(this);
    }

    public void removeOrderLine(OrderLine orderLine) {
        orderLine.setOrders(null);
        this.orderLines.remove(orderLine);
    }

}
