package org.bissis.services;

import org.bissis.domain.Customer;
import org.bissis.domain.DomainObject;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Markus Ullrich
 */
@Service
@Profile("map")
public class CustomerServiceImpl extends AbstractMapService implements CustomerService {

    @Override
    public List<DomainObject> listAll() {
        return new ArrayList<>(super.listAll());
    }

    @Override
    public Customer getById(Integer id) {
        return (Customer) super.getById(id);
    }

    @Override
    public Customer saveOrUpdate(Customer customer) {
        return (Customer) super.saveOrUpdate(customer);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

}
