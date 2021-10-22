package com.lerkin.titllist.dto;

import lombok.Value;

@Value
public class UserDto {

	Integer Id;
	String userName;
	String password;
	Role role;

}
