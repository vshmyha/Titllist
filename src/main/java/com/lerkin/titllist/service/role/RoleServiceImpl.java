package com.lerkin.titllist.service.role;

import com.lerkin.titllist.dao.DaoFactory;
import com.lerkin.titllist.dao.role.RoleDao;
import com.lerkin.titllist.dao.entity.Role;
import com.lerkin.titllist.dao.entity.User;

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

    @Override
    public void changeRole(User user) {
        roleDao.updateRole(user);
    }

    @Override
    public List<Role> getAvailableRoles(Role role) {
        return roleDao.selectAvailableRoles(role);
    }
}
