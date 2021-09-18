package com.lerkin.titllist.repository;

import com.lerkin.titllist.dao.entity_db.TypeEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<TypeEntity, Integer> {

	@Query("SELECT t FROM TypeEntity t JOIN AnimeEntity a ON a.type.id = t.id WHERE a.id = ?1")
	TypeEntity findTypeByAnimeId(Integer id);
}
