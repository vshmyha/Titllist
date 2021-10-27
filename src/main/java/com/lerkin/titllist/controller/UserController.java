package com.lerkin.titllist.controller;

import com.lerkin.titllist.dto.Role;
import com.lerkin.titllist.dto.UserDto;
import com.lerkin.titllist.security.common.Secured;
import com.lerkin.titllist.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Navigation.USER_ROLES)
public class UserController {

	private final UserService userService;

	@GetMapping(Navigation.ROLE)
	public ResponseEntity<?> getUserRole(HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("user");
		Role role = userDto.getRole();
		return ResponseEntity.ok(role);
	}

	@Secured
	@GetMapping
	public ResponseEntity<?> getUsersAndRoles(HttpSession session) {

		UserDto user = (UserDto) session.getAttribute("user");
		Role role = user.getRole();
		if (!role.isSimple()) {
			List<UserDto> users = userService.getUsersAndRoles();
			return ResponseEntity.ok(users);
		}
		return ResponseEntity.ok(null);
	}

	@Secured
	@PutMapping(Navigation.CHANGE)
	public ResponseEntity<?> changeRole(@RequestParam String role, @RequestParam Integer userId) {

		userService.changeRole(role, userId);
		return ResponseEntity.ok().build();
	}
}
