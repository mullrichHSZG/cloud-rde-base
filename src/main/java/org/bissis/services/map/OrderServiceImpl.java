package org.bissis.services.map;

import org.bissis.domain.DomainObject;
import org.bissis.domain.Orders;
import org.bissis.services.OrderService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Markus Ullrich
 */
@Service
@Profile("map")
public class OrderServiceImpl extends AbstractMapService implements OrderService {

    @Override
    public Orders saveOrUpdate(Orders domainObject) {
        return (Orders) super.saveOrUpdate(domainObject);
    }

    @Override
    public Orders getById(Integer id) {
        return (Orders) super.getById(id);
    }

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }
}
