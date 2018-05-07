package org.bissis.domain;

import javax.persistence.*;

/**
 * @author Markus Ullrich
 */
@Entity
public class OrderLine extends AbstractDomainClass {

    @ManyToOne
    private Product product;

    @ManyToOne
    private Orders orders;

    private Integer quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
