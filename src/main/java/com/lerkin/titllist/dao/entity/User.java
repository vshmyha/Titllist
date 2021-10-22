package com.lerkin.titllist.dao.entity;

import com.lerkin.titllist.dto.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends DBEntity {

	private String userName;
	private String password;
	private Role role;

	public String getUserName() {

		return userName;
	}
}
