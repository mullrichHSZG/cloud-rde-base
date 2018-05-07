package org.bissis.services;

import org.bissis.config.JpaIntegrationConfig;
import org.bissis.domain.Address;
import org.bissis.domain.Customer;
import org.bissis.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Markus Ullrich
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Import(JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class CustomerServiceJpaDaoImplTest {

    private CustomerService customerService;

    @Test
    public void listAll() throws Exception {
        List<Customer> customers = (List<Customer>) customerService.listAll();
        assert customers.size() == 3;
    }

    @Test
    public void saveOrUpdate() throws Exception {
        Customer newCustomer = new Customer();
        Address expectedBillingAddress = new Address();
        newCustomer.setBillingAddress(expectedBillingAddress);
        String zipCode = "12345";
        expectedBillingAddress.setZipCode(zipCode);
        String state = "Illinois";
        expectedBillingAddress.setState(state);
        String city = "Kansas";
        expectedBillingAddress.setCity(city);
        String email = "john@doe.com";
        newCustomer.setEmail(email);
        String phoneNumber = "123-456789";
        newCustomer.setPhoneNumber(phoneNumber);
        String firstName = "John";
        newCustomer.setFirstName(firstName);
        String lastName = "Doe";
        newCustomer.setLastName(lastName);
        String addressLineOne = "Balentine Drive 1";
        expectedBillingAddress.setAddressLineOne(addressLineOne);
        String addressLineTwo = "-";
        expectedBillingAddress.setAddressLineTwo(addressLineTwo);
        User user = new User();
        user.setUsername("user");
        user.setPassword("password");
        newCustomer.setUser(user);
        Customer savedCustomer = customerService.saveOrUpdate(newCustomer);
        assert savedCustomer.getId() != null;
        assert newCustomer.getBillingAddress().getZipCode().equals(savedCustomer.getBillingAddress().getZipCode());
        assert newCustomer.getBillingAddress().getCity().equals(savedCustomer.getBillingAddress().getCity());
        assert newCustomer.getBillingAddress().getState().equals(savedCustomer.getBillingAddress().getState());
        assert newCustomer.getEmail().equals(savedCustomer.getEmail());
        assert newCustomer.getPhoneNumber().equals(savedCustomer.getPhoneNumber());
        assert newCustomer.getFirstName().equals(savedCustomer.getFirstName());
        assert newCustomer.getLastName().equals(savedCustomer.getLastName());
        assert newCustomer.getBillingAddress().getAddressLineOne().equals(savedCustomer.getBillingAddress().getAddressLineOne());
        assert newCustomer.getBillingAddress().getAddressLineTwo().equals(savedCustomer.getBillingAddress().getAddressLineTwo());
        assert savedCustomer.getUser().getId() != null;
    }

    @Test
    public void delete() throws Exception {
        List<Customer> customers = (List<Customer>) customerService.listAll();
        customerService.delete(customers.get(0).getId());
        assert customerService.listAll().size() == 2;
    }

    @Test
    public void testSaveWithUser() {

        Customer customer = new Customer();
        User user = new User();
        user.setUsername("This is my user name");
        user.setPassword("MyAwesomePassword");
        customer.setUser(user);

        Customer savedCustomer = customerService.saveOrUpdate(customer);

        assert savedCustomer.getUser().getId() != null;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}