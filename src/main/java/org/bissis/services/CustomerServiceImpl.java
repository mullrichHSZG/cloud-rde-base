package org.bissis.services;

import org.bissis.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Markus Ullrich
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<Integer, Customer> customers;

    public CustomerServiceImpl(){
        initCustomerBase();
    }

    private void initCustomerBase(){
        this.customers = new HashMap<>();
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setFirstName("Anthony");
        customer1.setLastName("Adams");
        customer1.setEmail("anthony@adams.com");
        customer1.setPhoneNumber("111-11111");
        customer1.setAddressLineOne("Amber Alley 11");
        customer1.setAddressLineTwo("Apartment 111");
        customer1.setCity("Atlanta");
        customer1.setState("Georgia");
        customer1.setZipCode("30301");
        this.customers.put(1, customer1);

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setFirstName("Bertha");
        customer2.setLastName("Barnes");
        customer2.setEmail("bertha@barnes.com");
        customer2.setPhoneNumber("222-22222");
        customer2.setAddressLineOne("Balentine Boulevard 22");
        customer2.setAddressLineTwo("Basement");
        customer2.setCity("Boston");
        customer2.setState("Massachusetts");
        customer2.setZipCode("02222");
        this.customers.put(2, customer2);

        Customer customer3 = new Customer();
        customer3.setId(3);
        customer3.setFirstName("Charles");
        customer3.setLastName("Cartwright");
        customer3.setEmail("charles@cartwright.com");
        customer3.setPhoneNumber("333-33333");
        customer3.setAddressLineOne("Kane County");
        customer3.setAddressLineTwo("Carpentersville");
        customer3.setCity("Chicago");
        customer3.setState("Illinois");
        customer3.setZipCode("60110");
        this.customers.put(3, customer3);
    }

    @Override
    public List<Customer> listAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Customer findCustomerById(Integer id) {
        return customers.get(id);
    }

    private Integer getNextId() {
        return Collections.max(customers.keySet()) + 1;
    }

    @Override
    public Customer createOrUpdateCustomer(Customer customer) {
        if (customer != null) {
            if (customer.getId() == null) {
                customer.setId(getNextId());
            }
            this.customers.put(customer.getId(), customer);
            return customer;
        } else {
            throw new RuntimeException("Customer must not be null!");
        }
    }

    @Override
    public void deleteCustomer(Integer id) {
        this.customers.remove(id);
    }
}
