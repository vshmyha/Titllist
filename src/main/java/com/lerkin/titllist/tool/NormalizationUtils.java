package com.lerkin.titllist.tool;

import com.lerkin.titllist.dto.AnimeDto;

import java.util.List;

public interface NormalizationUtils {

	static void normalize(AnimeDto anime, List<Integer> genreIds, Integer typeId) {

		//		List<Genre> genres = genreIds.stream().map(id -> new Genre(id, null)).collect(Collectors.toList());
		//		anime.setGenres(genres);
		//		anime.setType(new Type(typeId, null));
	}
}
