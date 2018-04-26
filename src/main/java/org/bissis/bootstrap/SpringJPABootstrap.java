package org.bissis.bootstrap;

import org.bissis.domain.Customer;
import org.bissis.domain.Product;
import org.bissis.services.CustomerService;
import org.bissis.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Markus Ullrich
 */
@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ProductService productService;
    private CustomerService customerService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadProducts();
        loadCustomers();
    }

    private void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setFirstName("Anthony");
        customer1.setLastName("Adams");
        customer1.setEmail("anthony@adams.com");
        customer1.setPhoneNumber("111-11111");
        customer1.setAddressLineOne("Amber Alley 11");
        customer1.setAddressLineTwo("Apartment 111");
        customer1.setCity("Atlanta");
        customer1.setState("Georgia");
        customer1.setZipCode("30301");
        this.customerService.saveOrUpdate(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("Bertha");
        customer2.setLastName("Barnes");
        customer2.setEmail("bertha@barnes.com");
        customer2.setPhoneNumber("222-22222");
        customer2.setAddressLineOne("Balentine Boulevard 22");
        customer2.setAddressLineTwo("Basement");
        customer2.setCity("Boston");
        customer2.setState("Massachusetts");
        customer2.setZipCode("02222");
        this.customerService.saveOrUpdate(customer2);

        Customer customer3 = new Customer();
        customer3.setFirstName("Charles");
        customer3.setLastName("Cartwright");
        customer3.setEmail("charles@cartwright.com");
        customer3.setPhoneNumber("333-33333");
        customer3.setAddressLineOne("Kane County");
        customer3.setAddressLineTwo("Carpentersville");
        customer3.setCity("Chicago");
        customer3.setState("Illinois");
        customer3.setZipCode("60110");
        this.customerService.saveOrUpdate(customer3);
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
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
