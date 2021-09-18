package com.lerkin.titllist.service.role;

import com.lerkin.titllist.dao.entity.Role;
import com.lerkin.titllist.dao.entity.User;

import java.util.List;

public interface RoleService {

	List<Role> getRolesForAdmin();

	List<Role> getRolesForSuperAdmin();

	void changeRole(User user);

	List<Role> getAvailableRoles(Role role);
}
