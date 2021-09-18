package com.lerkin.titllist.tool;

import com.lerkin.titllist.dao.entity.Anime;
import com.lerkin.titllist.dao.entity.Genre;
import com.lerkin.titllist.dao.entity.Type;

import java.util.List;
import java.util.stream.Collectors;

public interface NormalizationUtils {

	static void normalize(Anime anime, List<Integer> genreIds, Integer typeId) {

		List<Genre> genres = genreIds.stream().map(id -> new Genre(id, null)).collect(Collectors.toList());
		anime.setGenres(genres);
		anime.setType(new Type(typeId, null));
	}
}
