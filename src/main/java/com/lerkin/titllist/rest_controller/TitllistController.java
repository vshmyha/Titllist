package com.lerkin.titllist.rest_controller;

import com.lerkin.titllist.dto.AnimeDto;
import com.lerkin.titllist.dto.UserDto;
import com.lerkin.titllist.service.anime.AnimeService;
import com.lerkin.titllist.tool.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Navigation.TITLLIST)
public class TitllistController {

	private final AnimeService animeService;

	@PostMapping(Navigation.ADD)
	public ResponseEntity<?> addAnimeToUserTitllist(HttpSession session, @RequestParam Integer animeId, @RequestParam String animeStatus) {

		UserDto user = (UserDto) session.getAttribute("user");
		Integer userId = user.getId();
		animeService.addAnimeToUserTitllist(userId, animeId, animeStatus);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(Navigation.CHANGE)
	public ResponseEntity<?> changeAnimeStatus(HttpSession session, @RequestParam String animeStatus, @RequestParam Integer animeId) {

		UserDto user = (UserDto) session.getAttribute("user");
		Integer userId = user.getId();
		animeService.checkCurrentAnimeStatus(animeId, userId, animeStatus);
		animeService.changeAnimeStatusInTitllist(animeId, userId, animeStatus);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> showUserTitllist(HttpSession session,
			@RequestParam(value = Navigation.STATUS_PARAM, required = false) String status) {

		UserDto user = (UserDto) session.getAttribute("user");
		Integer userId = user.getId();
		if (StringUtils.isNotEmpty(status)) {
			List<AnimeDto> notes = animeService.getAnimeFromUserTitllistByStatus(userId, status);
			return ResponseEntity.ok(notes);
		}
		List<AnimeDto> notes = animeService.getAnimeFromUserTitllist(userId);
		return ResponseEntity.ok(notes);
	}
}
