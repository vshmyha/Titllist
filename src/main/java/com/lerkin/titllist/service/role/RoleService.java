package com.lerkin.titllist.service.role;

import com.lerkin.titllist.dto.Role;

import java.util.List;

public interface RoleService {

	List<Role> getAvailableRoles(String role);
}
