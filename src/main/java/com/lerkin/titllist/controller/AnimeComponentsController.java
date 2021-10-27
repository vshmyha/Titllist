package com.lerkin.titllist.controller;

import com.lerkin.titllist.dto.Status;
import com.lerkin.titllist.dto.Type;
import com.lerkin.titllist.service.anime_component.AnimeComponentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(Navigation.ANIME_COMPONENT)
@RestController
@RequiredArgsConstructor
public class AnimeComponentsController {

	private final AnimeComponentService releaseDateService;

	@GetMapping(Navigation.DATE)
	public ResponseEntity<List<Short>> getReleaseDate() {

		List<Short> releaseDates = releaseDateService.getReleaseDate();
		return ResponseEntity.ok(releaseDates);
	}

	@GetMapping(Navigation.TYPE)
	public ResponseEntity<List<String>> getTypes() {

		List<String> types = Arrays.stream(Type.values()).map(Type::getTypeName).collect(Collectors.toList());
		return ResponseEntity.ok(types);
	}

	@GetMapping(Navigation.STATUS)
	public ResponseEntity<?> getAnimeStatus() {

		List<String> statusNames = Arrays.stream(Status.values())
				.map(Status::getStatusName)
				.collect(Collectors.toList());

		return ResponseEntity.ok(statusNames);
	}
}
