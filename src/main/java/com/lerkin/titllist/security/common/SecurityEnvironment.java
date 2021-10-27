package com.lerkin.titllist.security.common;

import com.lerkin.titllist.dto.UserDto;

public class SecurityEnvironment {

	private static final ThreadLocal<UserDto> USER = new ThreadLocal<>();

	public static void set(UserDto userDto) {
		USER.set(userDto);
	}

	public static UserDto get() {
		return USER.get();
	}
}
