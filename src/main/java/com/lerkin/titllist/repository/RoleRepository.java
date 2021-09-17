package com.lerkin.titllist.repository;


import com.lerkin.titllist.dao.entity_db.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {


}
