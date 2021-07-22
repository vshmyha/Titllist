package com.lerkin.titllist.service.authority;

import com.lerkin.titllist.dao.entity.Role;

public interface AuthorityService {

    Role userRole(Integer userId);
}
