package com.lerkin.titllist.dao.role;

import com.lerkin.titllist.dto.Role;
import com.lerkin.titllist.dao.entity.User;

import java.util.List;

public interface RoleDao {

	List<Role> selectRolesForAdmin();

	List<Role> selectRolesForSuperAdmin();

	void updateRole(User user);

	List<Role> selectAvailableRoles(Role role);
}
