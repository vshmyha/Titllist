package com.lerkin.titllist.dao.authority;

import com.lerkin.titllist.dto.Role;

public interface AuthorityDao {

	Role userRole(Integer userId);
}
