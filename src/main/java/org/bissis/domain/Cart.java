package org.bissis.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Markus Ullrich
 */
@Entity
public class Cart extends AbstractDomainClass {

    @OneToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart", orphanRemoval = true)
    private List<CartDetail> cartDetails = new ArrayList<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addCartDetail(CartDetail cartDetail) {
        this.cartDetails.add(cartDetail);
        cartDetail.setCart(this);
    }

    public void removeCartDetail(CartDetail cartDetail){
        this.cartDetails.remove(cartDetail);
        cartDetail.setCart(null);
    }

    public List<CartDetail> getCartDetails() {
        return this.cartDetails;
    }

    public void setCartDetails(List<CartDetail> cartDetails) {
        this.cartDetails = cartDetails;
    }

}
