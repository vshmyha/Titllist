package com.lerkin.titllist.repository;

import com.lerkin.titllist.dao.entity_db.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	UserEntity findByUserNameAndPassword(String username, String password);

	boolean existsByUserName(String username);

	UserEntity findByUserName(String userName);

	@Query("FROM UserEntity ORDER BY role.id DESC")
	List<UserEntity> findOrderByRole();

}
