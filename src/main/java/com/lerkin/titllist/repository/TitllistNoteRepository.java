package com.lerkin.titllist.repository;

import com.lerkin.titllist.entity.TitllistNoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TitllistNoteRepository extends JpaRepository<TitllistNoteEntity, Integer> {

	@Query("FROM TitllistNoteEntity WHERE id.animeId = ?1 AND id.userId = ?2")
	TitllistNoteEntity findFirstByAnimeAndUser(Integer animeId, Integer userId);

	@Query("FROM TitllistNoteEntity WHERE id.userId = ?1")
	List<TitllistNoteEntity> findByUser(Integer id);

	@Query("FROM TitllistNoteEntity WHERE id.userId = ?1 AND status = ?2")
	List<TitllistNoteEntity> findByUserAndStatus(Integer userId, String status);
}
