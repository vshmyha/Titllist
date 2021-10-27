package com.lerkin.titllist.service.user;

import com.lerkin.titllist.dto.UserDto;

import java.util.List;

public interface UserService {

	UserDto getUserByNameAndPass(UserDto user);

	void registration(UserDto user);

	void changePassword(UserDto user);

	void checkCurrentPassword(UserDto user);

	List<UserDto> getUsersAndRoles();

	UserDto getUserByUserName(String userName);
}
