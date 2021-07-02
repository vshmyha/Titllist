package com.lerkin.titllist.service.authority;

import com.lerkin.titllist.dao.DaoFactory;
import com.lerkin.titllist.dao.authority.AuthorityDao;
import com.lerkin.titllist.entity_db.Role;

public class AuthorityServiceImpl implements AuthorityService {
    private final AuthorityDao authorityDao = DaoFactory.getAuthorityDao();

    @Override
    public Role userRole(Integer userId) {
        return authorityDao.userRole(userId);
    }
}
