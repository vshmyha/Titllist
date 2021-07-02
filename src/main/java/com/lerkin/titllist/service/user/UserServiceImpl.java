package com.lerkin.titllist.service.user;

import com.lerkin.titllist.dao.DaoFactory;
import com.lerkin.titllist.dao.user.UserDao;
import com.lerkin.titllist.entity_db.Role;
import com.lerkin.titllist.entity_db.User;
import com.lerkin.titllist.exception.UserFriendlyException;
import com.lerkin.titllist.tool.EncryptionUtil;

import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = DaoFactory.getUserDao();

    @Override
    public User getUserByNameAndPass(User user) {
        String username = user.getUserName();
        String encodedPassword = EncryptionUtil.encodePassword(user);
        User resultUser = userDao.selectUser(username, encodedPassword);
        if (Objects.nonNull(resultUser)) {
            resultUser.setPassword(null);
        }
        return resultUser;
    }

    @Override
    public void registration(User user) {
        String username = user.getUserName();
        String encodedPassword = EncryptionUtil.encodePassword(user);
        user.setPassword(encodedPassword);
        user.setRole(Role.SIMPLE);
        boolean isUserExist = userDao.isUserExist(username);
        if (!isUserExist) {
            userDao.addUser(user);
        } else {
            throw new UserFriendlyException("User with this name already exist");
        }
    }

    @Override
    public void changePassword(User user) {
        String encodedPassword = EncryptionUtil.encodePassword(user);
        user.setPassword(encodedPassword);
        userDao.changePassword(user);
    }

    @Override
    public void checkCurrentPassword(User user) {
        user = getUserByNameAndPass(user);
        if (user == null) {
            throw new UserFriendlyException("Wrong current password");
        }
    }

    @Override
    public List<User> getUsersAndRoles() {
        return userDao.selectUsersAndRoles();
    }


}
