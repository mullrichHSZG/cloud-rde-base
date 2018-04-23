package org.bissis.services;

import org.bissis.domain.Customer;

import java.util.List;

/**
 * @author Markus Ullrich
 */
public interface CustomerService {

    List<Customer> listAllCustomers();

    Customer findCustomerById(Integer id);

    Customer createOrUpdateCustomer(Customer customer);

    void deleteCustomer(Integer id);

}
