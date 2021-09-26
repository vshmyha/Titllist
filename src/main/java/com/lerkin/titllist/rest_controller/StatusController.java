package com.lerkin.titllist.rest_controller;

import com.lerkin.titllist.dao.entity.Status;
import com.lerkin.titllist.dao.entity.User;
import com.lerkin.titllist.service.user_anime.UserAnimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(Navigation.STATUS)
public class StatusController {

	private final UserAnimeService userAnimeService;

	@GetMapping
	public ResponseEntity<?> getAnimeStatus() {

		List<String> statusNames = Arrays.stream(Status.values())
				.map(Status::getStatusName)
				.collect(Collectors.toList());

		return ResponseEntity.ok(statusNames);
	}

	@PutMapping(Navigation.CHANGE)
	public ResponseEntity<?> changeAnimeStatus(HttpSession session, @RequestParam String animeStatus, @RequestParam Integer animeId) {

		User user = (User) session.getAttribute("user");
		Integer userId = user.getId();
		Status status = Status.byText(animeStatus);
		userAnimeService.checkCurrentAnimeStatus(animeId, userId, status);
		userAnimeService.changeAnimeStatusInTitllist(animeId, userId, status);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
