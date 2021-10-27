package com.lerkin.titllist.repository;

import com.lerkin.titllist.dao.entity_db.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

	RoleEntity findByName(String roleName);

}
