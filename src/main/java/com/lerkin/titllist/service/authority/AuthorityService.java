package com.lerkin.titllist.service.authority;

import com.lerkin.titllist.entity_db.Role;

public interface AuthorityService {

    Role userRole(Integer userId);
}
