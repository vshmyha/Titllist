package com.lerkin.titllist.service.mapper;


import com.lerkin.titllist.dto.AnimeDto;
import com.lerkin.titllist.dto.GenreDto;
import com.lerkin.titllist.entity.AnimeEntity;
import com.lerkin.titllist.entity.GenreEntity;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface EntityMapper {

	static AnimeEntity toAnimeEntity(AnimeDto animeDto) {

		if (Objects.nonNull(animeDto)) {
			String type = animeDto.getType().getTypeName();
			List<GenreEntity> genreEntities = animeDto.getGenres().stream().map(EntityMapper::toGenreEntity).collect(Collectors.toList());
			return new AnimeEntity(animeDto.getRusName(), animeDto.getJapName(), type, animeDto.getEpisodes(),
					animeDto.getDuration(), animeDto.getReleaseDate(), genreEntities);
		}
		return null;
	}

	static GenreEntity toGenreEntity(GenreDto genreDto) {

		if (Objects.nonNull(genreDto)) {
			return new GenreEntity(genreDto.getName());
		}
		return null;
	}

}
