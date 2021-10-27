package com.lerkin.titllist.service.anime;

import com.lerkin.titllist.dto.AnimeDto;
import com.lerkin.titllist.dto.PaginationDto;
import com.lerkin.titllist.dto.TitllistNoteDto;
import com.lerkin.titllist.dto.filter.AnimeFilter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AnimeService {

	List<AnimeDto> getAnimesPage(AnimeFilter filter, PaginationDto pagination);

//	void addNewAnime(Anime anime);

	AnimeDto getAnimeById(Integer animeId);

	void addAnimeToUserTitllist(Integer userId, Integer animeId, String status);

	List<AnimeDto> getAnimeFromUserTitllist(Integer userId);

	List<AnimeDto> getAnimeFromUserTitllistByStatus(Integer userId, String status);

	List<AnimeDto> searchAnimesByName(String animeName);

	TitllistNoteDto getTitllistNote(Integer animeId, Integer userId);

	void checkCurrentAnimeStatus(Integer animeId, Integer userId, String status);

	void changeAnimeStatusInTitllist(Integer animeId, Integer userId, String status);

}
