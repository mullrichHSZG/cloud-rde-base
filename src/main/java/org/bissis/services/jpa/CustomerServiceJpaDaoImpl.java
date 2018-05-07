package org.bissis.services.jpa;

import org.bissis.domain.Customer;
import org.bissis.services.CustomerService;
import org.bissis.services.UserService;
import org.bissis.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * @author Markus Ullrich
 */
@Service
@Profile("jpadao")
public class CustomerServiceJpaDaoImpl extends AbstractJpaDaoService implements CustomerService {

    private EncryptionService encryptionService;

    @Override
    public List<?> listAll() {
        EntityManager em = emf.createEntityManager();
        List<Customer> result = em.createQuery("from Customer", Customer.class).getResultList();
        em.close();
        return result;
    }

    @Override
    public Customer getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        Customer result = em.find(Customer.class, id);
        em.close();
        return result;
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        if (domainObject.getUser() != null) {
            if (domainObject.getUser().getPassword() != null) {
                domainObject.getUser().setEncryptedPassword(
                        encryptionService.encryptString(domainObject.getUser().getPassword())
                );
            }
        }
        Customer result = em.merge(domainObject);
        em.getTransaction().commit();
        em.close();
        return result;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Customer.class, id));
        em.getTransaction().commit();
        em.close();
    }

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }
}
