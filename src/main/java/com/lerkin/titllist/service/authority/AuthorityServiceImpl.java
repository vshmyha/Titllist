package com.lerkin.titllist.service.authority;

import com.lerkin.titllist.dao.authority.AuthorityDao;
import com.lerkin.titllist.dao.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthorityServiceImpl implements AuthorityService {
    private final AuthorityDao authorityDao;

    @Override
    public Role userRole(Integer userId) {
        return authorityDao.userRole(userId);
    }
}
