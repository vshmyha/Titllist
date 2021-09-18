package com.lerkin.titllist.service.role;

import com.lerkin.titllist.dao.entity.Role;
import com.lerkin.titllist.dao.entity.User;
import com.lerkin.titllist.dao.role.RoleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class RoleServiceImpl implements RoleService {

	private final RoleDao roleDao;

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
