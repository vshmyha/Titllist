package com.lerkin.titllist.rest_controller;

import com.lerkin.titllist.dao.entity.Role;
import com.lerkin.titllist.dao.entity.User;
import com.lerkin.titllist.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Navigation.USER_ROLES)
public class UserController {

	private final UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> getUsersAndRoles(HttpSession session) {

		User user = (User) session.getAttribute("user");
		Role role = user.getRole();
		if (!role.isSimple()) {
			List<User> users = userService.getUsersAndRoles();
			return ResponseEntity.ok(users);
		}
		return ResponseEntity.ok(null);
		// return "redirect:start_page";
	}
}
