package org.bissis.services.map;

import org.bissis.domain.Role;
import org.bissis.services.RoleService;

/**
 * @author Markus Ullrich
 */
public class RoleServiceImpl extends AbstractMapService implements RoleService {

    @Override
    public Role getById(Integer id){
        return (Role) super.getById(id);
    }

    @Override
    public Role saveOrUpdate(Role domainObject) {
        return (Role) super.saveOrUpdate(domainObject);
    }
}
