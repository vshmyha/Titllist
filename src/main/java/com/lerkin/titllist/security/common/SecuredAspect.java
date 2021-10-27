package com.lerkin.titllist.security.common;

import com.lerkin.titllist.dto.Role;
import com.lerkin.titllist.dto.UserDto;
import com.lerkin.titllist.exception.AccessDeniedException;
import com.lerkin.titllist.exception.UnauthorizedException;
import com.lerkin.titllist.exception.UserIsBlockedException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Aspect
public class SecuredAspect {

	@Before("within(@org.springframework.web.bind.annotation.RestController *) && (@annotation(Secured) || @within(Secured))")
	public void onSecuredCall() {

		UserDto user = SecurityEnvironment.get();
		if (Objects.nonNull(user)) {
			Role role = user.getRole();
			if (!role.isAdmin() && !role.isSuperAdmin()) {
				throw new AccessDeniedException();
			}
			checkBlocked(user);
		} else {
			throw new UnauthorizedException();
		}
	}

	@Before("within(@org.springframework.web.bind.annotation.RestController *) "
			+ "&& !(@annotation(NoAuth) || @within(NoAuth))"
			+ "&& !(@annotation(Secured) || @within(Secured))")
	public void onAuthRequiredCall() {

		UserDto user = SecurityEnvironment.get();
		if (Objects.isNull(user)) {
			throw new UnauthorizedException();
		}
		checkBlocked(user);
	}

	private void checkBlocked(UserDto user) {

		Role role = user.getRole();
		if (role.isBlocked()) {
			throw new UserIsBlockedException();
		}
	}
}
