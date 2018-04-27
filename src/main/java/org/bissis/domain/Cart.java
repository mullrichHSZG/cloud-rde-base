package org.bissis.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Markus Ullrich
 */
@Entity
public class Cart implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    @OneToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart", orphanRemoval = true)
    private List<CartDetail> cartDetails = new ArrayList<>();

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void addCartDetail(CartDetail cartDetail) {
        this.cartDetails.add(cartDetail);
        cartDetail.setCart(this);
    }

    public void removeCartDetail(CartDetail cartDetail){
        cartDetail.setCart(null);
        this.cartDetails.remove(cartDetail);
    }

    public List<CartDetail> getCartDetails() {
        return this.cartDetails;
    }
}
