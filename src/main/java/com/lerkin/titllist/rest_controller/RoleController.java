package com.lerkin.titllist.rest_controller;

import com.lerkin.titllist.dto.Role;
import com.lerkin.titllist.dao.entity.User;
import com.lerkin.titllist.dto.UserDto;
import com.lerkin.titllist.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Navigation.ROLE)
public class RoleController {

	private final RoleService roleService;

	@GetMapping
	public ResponseEntity<List<Role>> getAvailableRoles(HttpSession session) {

		UserDto user = (UserDto) session.getAttribute("user");
		String role = user.getRole().getRoleName();
		List<Role> roles = roleService.getAvailableRoles(role);
		return ResponseEntity.ok(roles);
	}
}
