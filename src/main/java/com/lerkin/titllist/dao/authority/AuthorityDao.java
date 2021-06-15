package com.lerkin.titllist.dao.authority;

import com.lerkin.titllist.entity_db.Role;

public interface AuthorityDao {

    Role userRole(Integer userId);
}
