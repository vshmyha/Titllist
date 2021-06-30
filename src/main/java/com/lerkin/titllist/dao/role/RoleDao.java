package com.lerkin.titllist.dao.role;

import com.lerkin.titllist.entity_db.Role;
import com.lerkin.titllist.entity_db.User;

import java.util.List;

public interface RoleDao {

    List<Role> selectRolesForAdmin();

    List<Role> selectRolesForSuperAdmin();

    void updateRole(User user);

    List<Role> selectAvailableRoles(Role role);
}
