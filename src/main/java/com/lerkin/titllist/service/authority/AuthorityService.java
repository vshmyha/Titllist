package com.lerkin.titllist.service.authority;

import com.lerkin.titllist.dto.Role;

public interface AuthorityService {

	Role userRole(Integer userId);
}
