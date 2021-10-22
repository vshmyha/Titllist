package com.lerkin.titllist.service.role;

import com.lerkin.titllist.dao.entity_db.AvailableRoleEntity;
import com.lerkin.titllist.dao.entity_db.RoleEntity;
import com.lerkin.titllist.dto.Role;
import com.lerkin.titllist.dao.entity.User;
import com.lerkin.titllist.dao.role.RoleDao;
import com.lerkin.titllist.repository.AvailableRoleRepository;
import com.lerkin.titllist.repository.RoleRepository;
import com.lerkin.titllist.service.mapper.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class RoleServiceImpl implements RoleService {

	private final RoleRepository roleRepository;
	private final AvailableRoleRepository availableRoleRepository;

	@Override
	public List<Role> getAvailableRoles(String role) {

		RoleEntity entity = roleRepository.findByName(role);
		return availableRoleRepository.findByRole(entity).stream().map(DtoMapper::toRole).collect(Collectors.toList());
	}
}
