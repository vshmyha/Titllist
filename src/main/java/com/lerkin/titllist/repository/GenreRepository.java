package com.lerkin.titllist.repository;

import com.lerkin.titllist.dao.entity_db.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreEntity, Integer> {

}
