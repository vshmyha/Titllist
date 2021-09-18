package com.lerkin.titllist.dao.authority;

import com.lerkin.titllist.dao.entity.Role;

public interface AuthorityDao {

	Role userRole(Integer userId);
}
