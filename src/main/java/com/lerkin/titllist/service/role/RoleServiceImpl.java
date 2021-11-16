package com.lerkin.titllist.service.role;

import com.lerkin.titllist.dto.Role;
import com.lerkin.titllist.entity.RoleEntity;
import com.lerkin.titllist.entity.UserEntity;
import com.lerkin.titllist.exception.RequestedObjectNotFoundException;
import com.lerkin.titllist.repository.AvailableRoleRepository;
import com.lerkin.titllist.repository.RoleRepository;
import com.lerkin.titllist.repository.UserRepository;
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
	private final UserRepository userRepository;

	@Override
	public List<Role> getAvailableRoles(String role) {

		RoleEntity entity = roleRepository.findByName(role);
		return availableRoleRepository.findByRole(entity).stream().map(DtoMapper::toRole).collect(Collectors.toList());
	}

	@Override
	public void changeRole(String role, Integer userId) {

		UserEntity entity = userRepository.findById(userId).orElseThrow(() -> new RequestedObjectNotFoundException("user", userId));
		RoleEntity roleEntity = roleRepository.findByName(role);
		entity.setRole(roleEntity);
		userRepository.save(entity);
	}
}
