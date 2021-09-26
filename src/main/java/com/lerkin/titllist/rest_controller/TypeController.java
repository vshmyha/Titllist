package com.lerkin.titllist.rest_controller;

import com.lerkin.titllist.dao.entity_db.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(Navigation.TYPE)
@RestController
@RequiredArgsConstructor
public class TypeController {

	@GetMapping
	public ResponseEntity<List<String>> getTypes() {

		List<String> types = Arrays.stream(Type.values()).map(Type::getTypeName).collect(Collectors.toList());
		return ResponseEntity.ok(types);
	}
}
