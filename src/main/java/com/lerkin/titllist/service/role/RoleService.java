package com.lerkin.titllist.service.role;

import com.lerkin.titllist.dto.Role;
import com.lerkin.titllist.dao.entity.User;

import java.util.List;

public interface RoleService {

	List<Role> getAvailableRoles(String role);
}
