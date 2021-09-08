package com.lerkin.titllist.rest_controller;

import com.lerkin.titllist.service.release_date.ReleaseDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(Navigation.DATE)
@RestController
@RequiredArgsConstructor
public class ReleaseDateController {

    private final ReleaseDateService releaseDateService;

    @GetMapping
    public ResponseEntity<List<Short>> getReleaseDate() {
        List<Short> releaseDates = releaseDateService.getReleaseDate();
        return ResponseEntity.ok(releaseDates);
    }
}
