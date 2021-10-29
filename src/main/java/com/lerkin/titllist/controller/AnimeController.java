package com.lerkin.titllist.controller;

import com.lerkin.titllist.dto.AnimeDto;
import com.lerkin.titllist.dto.GenreDto;
import com.lerkin.titllist.dto.PaginationDto;
import com.lerkin.titllist.dto.TitllistNoteDto;
import com.lerkin.titllist.dto.UserDto;
import com.lerkin.titllist.dto.filter.AnimeFilter;
import com.lerkin.titllist.security.common.Secured;
import com.lerkin.titllist.service.anime.AnimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(Navigation.ANIME)
@RequiredArgsConstructor
public class AnimeController {

	private final AnimeService animeService;

	@GetMapping
	public ResponseEntity<List<AnimeDto>> getAnimesPage(AnimeFilter filter, PaginationDto pagination) {

		List<AnimeDto> animes = animeService.getAnimesPage(filter, pagination);
		return ResponseEntity.ok(animes);
	}

	@Secured
	@PostMapping(Navigation.ADD)
//		public ResponseEntity<?> addNewAnime(@RequestBody String data) {
	public ResponseEntity<?> addNewAnime(@RequestBody AnimeDto anime) {

		//		NormalizationUtils.normalize(anime, genreIds, typeId);
		String s = "dd";
		//		animeService.addNewAnime(anime);
		return ResponseEntity.ok().build();
	}

	@PostMapping(Navigation.SEARCH)
	public ResponseEntity<?> searchAnime(@RequestParam String animeName) {

		List<AnimeDto> animes = animeService.searchAnimesByName(animeName);
		return ResponseEntity.ok(animes);
	}

	@GetMapping(Navigation.BY_ID)
	public ResponseEntity<?> getAnimeById(HttpSession session, @PathVariable Integer id) {

		AnimeDto anime = animeService.getAnimeById(id);
		UserDto user = (UserDto) session.getAttribute("user");
		Integer userId = user.getId();
		TitllistNoteDto noteDto = animeService.getTitllistNote(id, userId);
		if (Objects.isNull(noteDto)) {
			TitllistNoteDto note = new TitllistNoteDto(anime, null, null);
			return ResponseEntity.ok(note);
		}
		return ResponseEntity.ok(noteDto);
	}
}
