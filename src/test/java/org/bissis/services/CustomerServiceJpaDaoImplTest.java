package org.bissis.services;

import org.bissis.config.JpaIntegrationConfig;
import org.bissis.domain.Customer;
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
    public void getById() throws Exception {
        Customer customer = customerService.getById(2);
        assert customer != null;
        customer = customerService.getById(4);
        assert customer == null;
    }

    @Test
    public void saveOrUpdate() throws Exception {
        Customer newCustomer = new Customer();
        String zipCode = "12345";
        newCustomer.setZipCode(zipCode);
        String state = "Illinois";
        newCustomer.setState(state);
        String city = "Kansas";
        newCustomer.setCity(city);
        String email = "john@doe.com";
        newCustomer.setEmail(email);
        String phoneNumber = "123-456789";
        newCustomer.setPhoneNumber(phoneNumber);
        String firstName = "John";
        newCustomer.setFirstName(firstName);
        String lastName = "Doe";
        newCustomer.setLastName(lastName);
        String addressLineOne = "Balentine Drive 1";
        newCustomer.setAddressLineOne(addressLineOne);
        String addressLineTwo = "-";
        newCustomer.setAddressLineTwo(addressLineTwo);
        Customer savedCustomer = customerService.saveOrUpdate(newCustomer);
        Integer id = 4;
        newCustomer.setId(id);
        assert newCustomer.getId().equals(savedCustomer.getId());
        assert newCustomer.getZipCode().equals(savedCustomer.getZipCode());
        assert newCustomer.getCity().equals(savedCustomer.getCity());
        assert newCustomer.getState().equals(savedCustomer.getState());
        assert newCustomer.getEmail().equals(savedCustomer.getEmail());
        assert newCustomer.getPhoneNumber().equals(savedCustomer.getPhoneNumber());
        assert newCustomer.getFirstName().equals(savedCustomer.getFirstName());
        assert newCustomer.getLastName().equals(savedCustomer.getLastName());
        assert newCustomer.getAddressLineOne().equals(savedCustomer.getAddressLineOne());
        assert newCustomer.getAddressLineTwo().equals(savedCustomer.getAddressLineTwo());
    }

    @Test
    public void delete() throws Exception {
        customerService.delete(1);
        assert customerService.listAll().size() == 2;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}