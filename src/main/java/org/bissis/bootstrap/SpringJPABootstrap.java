package org.bissis.bootstrap;

import org.bissis.domain.*;
import org.bissis.enums.OrderStatus;
import org.bissis.services.ProductService;
import org.bissis.services.RoleService;
import org.bissis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Markus Ullrich
 */
@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ProductService productService;
    private UserService userService;
    private RoleService roleService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadProducts();
        loadUsersAndCustomers();
        loadCarts();
        loadOrderHistory();
        loadRoles();
        assignUsersToDefaultRole();
    }

    private void assignUsersToDefaultRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role -> {
            if(role.getRole().equalsIgnoreCase("CUSTOMER")){
                users.forEach(user -> {
                    user.addRole(role);
                    userService.saveOrUpdate(user);
                });
            }
        });
    }

    private void loadRoles() {
        Role role = new Role();
        role.setRole("CUSTOMER");
        roleService.saveOrUpdate(role);
    }

    private void loadOrderHistory() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();

        users.forEach(user ->{
            Orders orders = new Orders();
            orders.setCustomer(user.getCustomer());
            orders.setOrderStatus(OrderStatus.SHIPPED);

            products.forEach(product -> {
                OrderLine orderLine = new OrderLine();
                orderLine.setProduct(product);
                orderLine.setQuantity(1);
                orders.addOrderLine(orderLine);
            });
            userService.saveOrUpdate(user);
        });
    }

    private void loadCarts() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();

        users.forEach(user -> {
            user.setCart(new Cart());
            CartDetail cartDetail = new CartDetail();
            cartDetail.setProduct(products.get(0));
            cartDetail.setQuantity(2);
            user.getCart().addCartDetail(cartDetail);
            userService.saveOrUpdate(user);
        });
    }

    private void loadUsersAndCustomers() {
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("passwd");

        Customer customer1 = new Customer();
        customer1.setFirstName("Anthony");
        customer1.setLastName("Adams");
        customer1.setEmail("anthony@adams.com");
        customer1.setPhoneNumber("111-11111");
        customer1.setBillingAddress(new Address());
        customer1.getBillingAddress().setAddressLineOne("Amber Alley 11");
        customer1.getBillingAddress().setAddressLineTwo("Apartment 111");
        customer1.getBillingAddress().setCity("Atlanta");
        customer1.getBillingAddress().setState("Georgia");
        customer1.getBillingAddress().setZipCode("30301");

        user1.setCustomer(customer1);
        this.userService.saveOrUpdate(user1);

        User user2 = new User();
        user2.setUsername("benny");
        user2.setPassword("123456");

        Customer customer2 = new Customer();
        customer2.setFirstName("Bertha");
        customer2.setLastName("Barnes");
        customer2.setEmail("bertha@barnes.com");
        customer2.setPhoneNumber("222-22222");
        customer2.setBillingAddress(new Address());
        customer2.getBillingAddress().setAddressLineOne("Balentine Boulevard 22");
        customer2.getBillingAddress().setAddressLineTwo("Basement");
        customer2.getBillingAddress().setCity("Boston");
        customer2.getBillingAddress().setState("Massachusetts");
        customer2.getBillingAddress().setZipCode("02222");

        user2.setCustomer(customer2);
        this.userService.saveOrUpdate(user2);

        User user3 = new User();
        user3.setUsername("clara");
        user3.setPassword("iughqp98fdpaq");

        Customer customer3 = new Customer();
        customer3.setFirstName("Charles");
        customer3.setLastName("Cartwright");
        customer3.setEmail("charles@cartwright.com");
        customer3.setPhoneNumber("333-33333");
        customer3.setBillingAddress(new Address());
        customer3.getBillingAddress().setAddressLineOne("Kane County");
        customer3.getBillingAddress().setAddressLineTwo("Carpentersville");
        customer3.getBillingAddress().setCity("Chicago");
        customer3.getBillingAddress().setState("Illinois");
        customer3.getBillingAddress().setZipCode("60110");

        user3.setCustomer(customer3);
        this.userService.saveOrUpdate(user3);
    }

    public void loadProducts() {
        Product product1 = new Product();
        product1.setDescription("Product 1");
        product1.setPrice(new BigDecimal("12.99"));
        product1.setImageUrl("http://example.com/product1");

        productService.saveOrUpdate(product1);

        Product product2 = new Product();
        product2.setDescription("Product 2");
        product2.setPrice(new BigDecimal("14.99"));
        product2.setImageUrl("http://example.com/product2");

        productService.saveOrUpdate(product2);

        Product product3 = new Product();
        product3.setDescription("Product 3");
        product3.setPrice(new BigDecimal("23.99"));
        product3.setImageUrl("http://example.com/product3");

        productService.saveOrUpdate(product3);

        Product product4 = new Product();
        product4.setDescription("Product 4");
        product4.setPrice(new BigDecimal("42.99"));
        product4.setImageUrl("http://example.com/product4");

        productService.saveOrUpdate(product4);

        Product product5 = new Product();
        product5.setDescription("Product 5");
        product5.setPrice(new BigDecimal("45.99"));
        product5.setImageUrl("http://example.com/product5");

        productService.saveOrUpdate(product5);
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
}
