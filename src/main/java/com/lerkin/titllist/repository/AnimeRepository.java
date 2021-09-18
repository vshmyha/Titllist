package com.lerkin.titllist.repository;

import com.lerkin.titllist.dao.entity_db.AnimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimeRepository extends JpaRepository<AnimeEntity, Integer> {

	//    @Query("SELECT NEW AnimeEntity (a.id, a.rusName, a.japName) FROM AnimeEntity a")
	//    List<AnimeEntity> findAllShort();

	@Query("SELECT a FROM AnimeEntity a JOIN GenreEntity g WHERE g.id = ?1")
	List<AnimeEntity> findByGenreId(Integer id);

	//
	//    @Query("SELECT a.id, a.rusName, a.japName FROM AnimeEntity a JOIN TypeEntity t WHERE a.type.id = ?1")
	//    List<AnimeEntity> findByTypeId(Integer id);
	//
	//
	List<AnimeEntity> findAnimeEntityByReleaseDate(Short releaseDate);

	AnimeEntity findAnimeEntityById(Integer id);

	//    @Query("SELECT a.id, a.rusName, a.japName FROM AnimeEntity a JOIN UserEntity u WHERE u.id= ?1")
	//    List<AnimeEntity> findByUserId(Integer id);
	//
	//    @Query("SELECT id, rusName, japName FROM AnimeEntity WHERE rusName LIKE ?1 OR japName LIKE ?2")
	//    List<AnimeEntity> findByRusNameLikeOrJapNameLike(String name);
}
