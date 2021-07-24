package com.lerkin.titllist.rest_controller;

import com.lerkin.titllist.dao.entity.Status;
import com.lerkin.titllist.service.status.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Navigation.STATUS)
public class StatusController {

    private final StatusService statusService;

    @GetMapping
    public ResponseEntity<List<Status>> getAnimeStatus() {
        List<Status> statuses = statusService.getStatuses();
        return ResponseEntity.ok(statuses);
    }
}
