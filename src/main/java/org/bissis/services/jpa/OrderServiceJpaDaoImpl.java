package org.bissis.services.jpa;

import org.bissis.domain.Orders;
import org.bissis.services.OrderService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Markus Ullrich
 */
@Service
@Profile("jpadao")
public class OrderServiceJpaDaoImpl extends AbstractJpaDaoService implements OrderService {

    @Override
    public List<Orders> listAll() {
        EntityManager em = emf.createEntityManager();
        List<Orders> orderList = em.createQuery("find Order", Orders.class).getResultList();
        em.close();
        return orderList;
    }

    @Override
    public Orders getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        Orders orders = em.find(Orders.class, id);
        em.close();
        return orders;
    }

    @Override
    public Orders saveOrUpdate(Orders domainObject) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Orders orders = em.merge(domainObject);
        em.getTransaction().commit();
        em.close();
        return orders;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Orders.class, id));
        em.getTransaction().commit();
        em.close();
    }
}
