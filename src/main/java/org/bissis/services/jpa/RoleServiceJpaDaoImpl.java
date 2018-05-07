package org.bissis.services.jpa;

import org.bissis.domain.Role;
import org.bissis.services.RoleService;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Markus Ullrich
 */
public class RoleServiceJpaDaoImpl extends AbstractJpaDaoService implements RoleService {

    @Override
    public List<Role> listAll() {
        EntityManager em = emf.createEntityManager();
        List<Role> result = em.createQuery("from Role", Role.class).getResultList();
        em.close();
        return result;
    }

    @Override
    public Role getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        Role result = em.find(Role.class, id);
        em.close();
        return result;
    }

    @Override
    public Role saveOrUpdate(Role domainObject) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Role savedProduct = em.merge(domainObject);
        em.getTransaction().commit();
        em.close();
        return savedProduct;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Role.class, id));

        em.getTransaction().commit();
        em.close();
    }

}
