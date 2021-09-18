package com.lerkin.titllist.rest_controller;

import com.lerkin.titllist.dao.entity.Role;
import com.lerkin.titllist.dao.entity.User;
import com.lerkin.titllist.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Navigation.ROLE)
public class RoleController {

	private final RoleService roleService;

	@PutMapping(Navigation.CHANGE)
	public ResponseEntity<?> changeRole(@RequestParam String role, @RequestParam Integer userId) {

		User user = new User();
		Role roleName = Role.valueOf(role);
		user.setRole(roleName);
		user.setId(userId);
		roleService.changeRole(user);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<List<Role>> getAvailableRoles(HttpSession session) {

		User user = (User) session.getAttribute("user");
		Role role = user.getRole();
		List<Role> roles = roleService.getAvailableRoles(role);
		return ResponseEntity.ok(roles);
	}
}
