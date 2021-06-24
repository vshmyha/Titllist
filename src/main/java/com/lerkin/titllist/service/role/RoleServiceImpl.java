package com.lerkin.titllist.service.role;

import com.lerkin.titllist.dao.DaoFactory;
import com.lerkin.titllist.dao.role.RoleDao;
import com.lerkin.titllist.entity_db.Role;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao = DaoFactory.getRoleDao();

    @Override
    public List<Role> getRolesForAdmin() {
        return roleDao.selectRolesForAdmin();
    }

    @Override
    public List<Role> getRolesForSuperAdmin() {
        return roleDao.selectRolesForSuperAdmin();
    }
}
