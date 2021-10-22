package com.lerkin.titllist.rest_controller;

import com.lerkin.titllist.dto.GenreDto;
import com.lerkin.titllist.service.genre.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(Navigation.GENRE)
@RestController
@RequiredArgsConstructor
public class GenreController {

	private final GenreService genreService;

	@GetMapping
	public ResponseEntity<?> getGenres() {

		List<GenreDto> genres = genreService.getGenres();
		return ResponseEntity.ok(genres);
	}

}
