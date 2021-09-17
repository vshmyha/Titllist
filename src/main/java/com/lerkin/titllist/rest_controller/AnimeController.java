package com.lerkin.titllist.rest_controller;

import com.lerkin.titllist.dao.entity.Anime;
import com.lerkin.titllist.dao.entity_db.AnimeEntity;
import com.lerkin.titllist.service.anime.AnimeService;
import com.lerkin.titllist.tool.NormalizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Navigation.ANIME)
@RequiredArgsConstructor
public class AnimeController {

    private final AnimeService animeService;

    @GetMapping
    public ResponseEntity<List<AnimeEntity>> getAllAnime() {
        List<AnimeEntity> animes = animeService.getAllAnime();
        return ResponseEntity.ok(animes);
    }

    @PostMapping(Navigation.ADD)
    public ResponseEntity<?> addNewAnime(Anime anime, @RequestParam("genres") List<Integer> genreIds, @RequestParam("type") Integer typeId) {
        NormalizationUtils.normalize(anime, genreIds, typeId);
        animeService.addNewAnime(anime);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping(Navigation.SEARCH)
    public ResponseEntity<List<Anime>> searchAnime(@RequestParam String animeName) {
        List<Anime> animes = animeService.searchAnimesByName(animeName);
        return ResponseEntity.ok(animes);
    }

    @GetMapping(Navigation.TYPE + Navigation.ID_PARAM)
    public ResponseEntity<List<Anime>> getAnimeByType(@PathVariable Integer id) {
        List<Anime> animeByTypes = animeService.getAnimeByTypes(id);
        return ResponseEntity.ok(animeByTypes);
    }

    @GetMapping(Navigation.GENRE + Navigation.ID_PARAM)
    public ResponseEntity<List<AnimeEntity>> getAnimeByGenre(@PathVariable Integer id) {
        List<AnimeEntity> animeByGenres = animeService.getAnimeByGenres(id);
        return ResponseEntity.ok(animeByGenres);
    }

    @GetMapping(Navigation.DATE + Navigation.VALUE)
    public ResponseEntity<List<Anime>> getAnimeReleaseDate(@PathVariable("value") Integer dateValue) {
        List<Anime> animeByReleaseDate = animeService.getAnimeByReleaseDate(dateValue);
        return ResponseEntity.ok(animeByReleaseDate);
    }
}
