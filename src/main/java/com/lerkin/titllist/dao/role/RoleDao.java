package com.lerkin.titllist.dao.role;

import com.lerkin.titllist.entity_db.Role;

import java.util.List;

public interface RoleDao {

    List<Role> selectRolesForAdmin();

    List<Role> selectRolesForSuperAdmin();
}
