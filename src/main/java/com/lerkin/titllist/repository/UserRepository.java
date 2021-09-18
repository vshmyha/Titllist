package com.lerkin.titllist.repository;

import com.lerkin.titllist.dao.entity_db.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	UserEntity findUserByUserNameAndPassword(String username, String password);

	UserEntity findUserByUserName(String username);

}
