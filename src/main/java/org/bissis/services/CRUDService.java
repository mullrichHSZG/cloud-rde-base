package org.bissis.services;

import java.util.List;

/**
 * @author Markus Ullrich
 */
public interface CRUDService<T> {
    List<?> listAll();

    T getById(Integer id);

    T saveOrUpdate(T domainObject);

    void delete(Integer id);
}
