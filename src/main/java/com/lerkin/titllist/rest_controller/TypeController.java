package com.lerkin.titllist.rest_controller;

import com.lerkin.titllist.dao.entity_db.TypeEntity;
import com.lerkin.titllist.service.type.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(Navigation.TYPE)
@RestController
@RequiredArgsConstructor
public class TypeController {

	private final TypeService typeService;

	@GetMapping
	public ResponseEntity<List<TypeEntity>> getTypes() {

		List<TypeEntity> types = typeService.getTypes();
		return ResponseEntity.ok(types);
	}
}
