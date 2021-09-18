package com.lerkin.titllist.rest_controller;

import com.lerkin.titllist.dao.entity.Role;
import com.lerkin.titllist.dao.entity.User;
import com.lerkin.titllist.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {

	private final UserService userService;

	@PostMapping(Navigation.LOGIN)
	public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest req) {

		User user = new User(username, password, null);
		user = userService.getUserByNameAndPass(user);
		if (user != null) {
			Role role = user.getRole();
			if (!role.isBlocked()) {
				HttpSession session = req.getSession();
				session.setAttribute("user", user);
				return "redirect:main_page";
			} else {
				return "redirect:blocked_page";
			}
		} else {
			req.setAttribute("response", "Wrong login or password");
			return "redirect:start_page";
		}
	}

	@GetMapping(Navigation.LOGOUT)
	public String logout(HttpServletRequest req) {

		HttpSession session = req.getSession();
		session.invalidate();
		return "redirect:start_page";
	}

	@PostMapping(Navigation.REGISTRATION)
	public ResponseEntity<?> registration(@RequestParam String username, @RequestParam String password, HttpServletRequest req) {

		User user = new User(username, password, null);
		userService.registration(user);
		HttpSession session = req.getSession();
		session.setAttribute("user", user);
		return ResponseEntity.ok().build();
	}

	@PutMapping(Navigation.CHANGE)
	public ResponseEntity<?> changePassword(@RequestParam String currentPassword, @RequestParam String newPassword, HttpSession session) {

		User user = (User) session.getAttribute("user");
		String username = user.getUserName();
		User fullUser = new User(username, currentPassword, null);
		userService.checkCurrentPassword(fullUser);
		fullUser.setPassword(newPassword);
		userService.changePassword(fullUser);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
