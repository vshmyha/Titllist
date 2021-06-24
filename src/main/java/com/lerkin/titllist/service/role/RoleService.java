package com.lerkin.titllist.service.role;

import com.lerkin.titllist.entity_db.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRolesForAdmin();

    List<Role> getRolesForSuperAdmin();
}
