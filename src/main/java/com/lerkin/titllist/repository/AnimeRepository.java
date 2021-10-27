package com.lerkin.titllist.repository;

import com.lerkin.titllist.dao.entity_db.AnimeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnimeRepository extends JpaRepository<AnimeEntity, Integer> {

	Page<AnimeEntity> findByGenres_Id(Integer id, Pageable pageable);

	@Query("SELECT DISTINCT releaseDate FROM AnimeEntity ORDER BY releaseDate")
	List<Short> findDistinctReleaseDates();

	Page<AnimeEntity> findByType(String type, Pageable pageable);

	Page<AnimeEntity> findByReleaseDate(Short releaseDate, Pageable pageable);

	List<AnimeEntity> findByRusNameContainsOrJapNameContains(String rusName, String japName);
}
