package com.lerkin.titllist.repository;

import com.lerkin.titllist.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreEntity, Integer> {

	GenreEntity findByName(String genreName);

}
