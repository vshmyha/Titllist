package com.lerkin.titllist.service.role;

import com.lerkin.titllist.entity_db.Role;
import com.lerkin.titllist.entity_db.User;

import java.util.List;

public interface RoleService {

    List<Role> getRolesForAdmin();

    List<Role> getRolesForSuperAdmin();

    void changeRole(User user);

    List<Role> getAvailableRoles(Role role);
}
