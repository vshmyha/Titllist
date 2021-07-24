package com.lerkin.titllist.rest_controller;

import com.lerkin.titllist.controller.response.ResponseType;
import com.lerkin.titllist.controller.response.ResponseUtil;
import com.lerkin.titllist.dao.entity.*;
import com.lerkin.titllist.service.anime.AnimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(Navigation.ANIME)
@RequiredArgsConstructor
public class AnimeController {

    private final AnimeService animeService;

    @GetMapping
    public ResponseEntity<List<Anime>> getAllAnime() throws IllegalAccessException {
        List<Anime> animes = animeService.getAllAnime();
        return ResponseEntity.ok(animes);
    }

    //TODO: have a question about response, signature and js realisation
    @PostMapping(Navigation.ADD_ANIME_TO_USER_TITLLIST)
    public ResponseEntity<?> addAnimeToUserTitllist(HttpServletRequest req, @RequestParam Integer animeId, @RequestParam String animeStatus) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Status status = Status.byText(animeStatus);
        animeService.addAnimeToUserTitllist(user, animeId, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //TODO: response and signature, js
    @PostMapping(Navigation.ADD_NEW_ANIME)
    public ResponseEntity<?> addNewAnime(@RequestParam String[] genre, @RequestParam String rusName, @RequestParam String japName, @RequestParam Integer type,
                                         @RequestParam Integer duration, @RequestParam Integer episodes, @RequestParam Short releaseDate) {
        Type animeType = new Type();
        animeType.setId(type);
        List<Genre> genres = new ArrayList<>();
        for (String s : genre) {
            Genre animeGenre = new Genre();
            Integer id = Integer.valueOf(s);
            animeGenre.setId(id);
            genres.add(animeGenre);
        }
        Anime anime = new Anime(rusName, japName, animeType, episodes, duration, releaseDate, genres);
        animeService.addNewAnime(anime);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //TODO: does not coming in method
    @GetMapping(Navigation.SHOW_USER_TITLLIST)
    public ResponseEntity<List<Anime>> showUserTitllist(HttpServletRequest req, @RequestParam String status) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Anime> animes;
        if (status == null) {
            animes = animeService.getAnimeFromUserTitllist(user);
        } else {
            if (status.startsWith("In")) {
                status = "In process";
            }
            Status statusName = Status.byText(status);
            animes = animeService.getAnimeFromUserTitllistByStatus(user, statusName);
        }
        return ResponseEntity.ok(animes);
    }

    //TODO: dose not come in, js
    @GetMapping(Navigation.SEARCH)
    public ResponseEntity<List<Anime>> searchAnime(@RequestParam String animeName) {
        List<Anime> animes = animeService.searchAnimesByName(animeName);
        return ResponseEntity.ok(animes);
    }

    //TODO: question about navigation, js, x3
    @GetMapping
    public ResponseEntity<List<Anime>> getAnimeByType(@RequestParam Integer id) {
        List<Anime> animeByTypes = animeService.getAnimeByTypes(id);
        return ResponseEntity.ok(animeByTypes);
    }

    @GetMapping
    public ResponseEntity<List<Anime>> getAnimeByGenre(@RequestParam Integer id) {
        List<Anime> animeByGenres = animeService.getAnimeByGenres(id);
        return ResponseEntity.ok(animeByGenres);
    }

    @GetMapping
    public ResponseEntity<List<Anime>> getAnimeReleaseDate(@RequestParam Integer id) {
        List<Anime> animeByReleaseDate = animeService.getAnimeByReleaseDate(id);
        return ResponseEntity.ok(animeByReleaseDate);
    }
}
