package com.lerkin.titllist.security.filter;

import com.lerkin.titllist.dto.UserDto;
import com.lerkin.titllist.security.common.SecurityEnvironment;
import com.lerkin.titllist.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class RoleUpdatingFilter extends OncePerRequestFilter {

	private final UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

		HttpSession session = httpServletRequest.getSession(false);
		if (Objects.nonNull(session)) {
			UserDto userDto = (UserDto) session.getAttribute("user");
			if (Objects.nonNull(userDto)) {
				UserDto user = userService.getUserByUserName(userDto.getUserName());
				SecurityEnvironment.set(user);
				session.setAttribute("user", user);
			} else {
				SecurityEnvironment.set(null);
			}
		} else {
			SecurityEnvironment.set(null);
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}
