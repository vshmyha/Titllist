package com.lerkin.titllist.service.user;

import com.lerkin.titllist.dto.Role;
import com.lerkin.titllist.dao.entity_db.RoleEntity;
import com.lerkin.titllist.dao.entity_db.UserEntity;
import com.lerkin.titllist.dto.UserDto;
import com.lerkin.titllist.exception.RequestedObjectNotFoundException;
import com.lerkin.titllist.exception.UserFriendlyException;
import com.lerkin.titllist.repository.RoleRepository;
import com.lerkin.titllist.repository.UserRepository;
import com.lerkin.titllist.service.mapper.DtoMapper;
import com.lerkin.titllist.tool.EncryptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	@Override
	public UserDto getUserByNameAndPass(UserDto user) {

		String username = user.getUserName();
		String encodedPassword = EncryptionUtil.encodePassword(user);
		UserEntity resultUser = userRepository.findByUserNameAndPassword(username, encodedPassword);
		if (Objects.nonNull(resultUser)) {
			resultUser.setPassword(null);
		}
		return DtoMapper.toUserDto(resultUser);
	}

	@Override
	public void registration(UserDto user) {

		String username = user.getUserName();
		String encodedPassword = EncryptionUtil.encodePassword(user);
		UserDto fullUser = new UserDto(null, username, encodedPassword, Role.SIMPLE);
		if (!userRepository.existsByUserName(username)) {
			RoleEntity role = roleRepository.findByName(fullUser.getRole().getRoleName());
			UserEntity entity = new UserEntity(fullUser.getUserName(), fullUser.getPassword(), role);
			userRepository.save(entity);
		} else {
			throw new UserFriendlyException("User with this name already exist");
		}
	}

	@Override
	public void changePassword(UserDto user) {

		String encodedPassword = EncryptionUtil.encodePassword(user);
		UserEntity userEntity = userRepository.findByUserName(user.getUserName());
		userEntity.setPassword(encodedPassword);
		userRepository.save(userEntity);
	}

	@Override
	public void checkCurrentPassword(UserDto user) {

		user = getUserByNameAndPass(user);
		if (user == null) {
			throw new UserFriendlyException("Wrong current password");
		}
	}

	@Override
	public List<UserDto> getUsersAndRoles() {

		return userRepository.findOrderByRole().stream().peek(userEntity -> userEntity.setPassword(null)).map(DtoMapper::toUserDto).collect(Collectors.toList());
	}

	@Override
	public void changeRole(String role, Integer userId) {

		UserEntity entity = userRepository.findById(userId).orElseThrow(() -> new RequestedObjectNotFoundException("user", userId));
		RoleEntity roleEntity = roleRepository.findByName(role);
		entity.setRole(roleEntity);
		userRepository.save(entity);
	}

}
