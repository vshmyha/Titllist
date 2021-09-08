package com.lerkin.titllist.rest_controller;

import com.lerkin.titllist.dao.entity.Anime;
import com.lerkin.titllist.dao.entity.Status;
import com.lerkin.titllist.dao.entity.User;
import com.lerkin.titllist.service.anime.AnimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Navigation.TITLLIST)
public class TitllistController {

    private final AnimeService animeService;

    //TODO: js realisation
    @PostMapping(Navigation.ADD)
    public ResponseEntity<?> addAnimeToUserTitllist(HttpSession session, @RequestParam Integer animeId, @RequestParam String animeStatus) {
        User user = (User) session.getAttribute("user");
        Status status = Status.byText(animeStatus);
        animeService.addAnimeToUserTitllist(user, animeId, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Anime>> showUserTitllist(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Anime> animes = animeService.getAnimeFromUserTitllist(user);
        return ResponseEntity.ok(animes);
    }

    @GetMapping(Navigation.STATUS_PARAM)
    public ResponseEntity<List<Anime>> showUserTitllist(HttpSession session, @PathVariable String status) {
        User user = (User) session.getAttribute("user");
        if (status.startsWith("In")) {
            status = "In process";
        }
        Status statusName = Status.byText(status);
        List<Anime> animes = animeService.getAnimeFromUserTitllistByStatus(user, statusName);
        return ResponseEntity.ok(animes);
    }
}
