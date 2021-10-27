package com.lerkin.titllist.security.common;

import com.lerkin.titllist.dto.Role;
import com.lerkin.titllist.dto.UserDto;
import com.lerkin.titllist.exception.AccessDeniedException;
import org.aspectj.lang.JoinPoint;
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
			if (!role.isAdmin() || !role.isSuperAdmin()) {
				throw new AccessDeniedException();
			}
		} else {
			throw new AccessDeniedException();
		}
	}
}
