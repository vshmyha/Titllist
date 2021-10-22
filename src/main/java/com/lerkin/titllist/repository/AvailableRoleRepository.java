package com.lerkin.titllist.repository;

import com.lerkin.titllist.dao.entity_db.AvailableRoleEntity;
import com.lerkin.titllist.dao.entity_db.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AvailableRoleRepository extends JpaRepository<AvailableRoleEntity, Integer> {

	@Query("FROM AvailableRoleEntity WHERE role = ?1")
	List<AvailableRoleEntity> findByRole(RoleEntity role);
}
