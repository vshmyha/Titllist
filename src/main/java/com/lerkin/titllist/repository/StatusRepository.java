package com.lerkin.titllist.repository;

import com.lerkin.titllist.dao.entity_db.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<StatusEntity, Integer> {

    List<StatusEntity> findAll();
}
