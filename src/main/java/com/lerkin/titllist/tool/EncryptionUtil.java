package com.lerkin.titllist.tool;


import com.lerkin.titllist.dto.UserDto;

import java.util.Base64;

public class EncryptionUtil {

	private EncryptionUtil() {

	}

	public static String encodePassword(UserDto user) {

		String password = user.getPassword();
		byte[] encode = Base64.getEncoder().encode(password.getBytes());
		String encodedPassword = new String(encode);
		return encodedPassword;
	}
}
