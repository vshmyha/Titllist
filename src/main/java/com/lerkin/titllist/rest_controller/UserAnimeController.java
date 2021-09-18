package com.lerkin.titllist.rest_controller;

import com.lerkin.titllist.dao.entity.Anime;
import com.lerkin.titllist.dao.entity.User;
import com.lerkin.titllist.dao.entity.UserAnime;
import com.lerkin.titllist.dao.entity_db.AnimeEntity;
import com.lerkin.titllist.service.anime.AnimeService;
import com.lerkin.titllist.service.user_anime.UserAnimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping(Navigation.ANIME)
public class UserAnimeController {

	private final AnimeService animeService;
	private final UserAnimeService userAnimeService;

	@GetMapping(Navigation.ID_PARAM)
	public ResponseEntity<AnimeEntity> getAnimeById(HttpSession session, @PathVariable Integer id) {
		//        User user = (User) session.getAttribute("user");
		//        Integer userId = user.getId();
		//        UserAnime userAnime = userAnimeService.getUserAnime(id, userId);
		//        if (userAnime == null) {
		AnimeEntity anime = animeService.getAnimeById(id);
		//            userAnime = new UserAnime(anime, null, userId);
		//        }
		return ResponseEntity.ok(anime);
	}
}
