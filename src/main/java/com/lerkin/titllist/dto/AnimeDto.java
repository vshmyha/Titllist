package com.lerkin.titllist.dto;

import lombok.Value;

import java.util.List;

@Value
public class AnimeDto {

	Integer id;
	String rusName;
	String japName;
	Type type;
	Integer episodes;
	Integer duration;
	Short releaseDate;
	List<GenreDto> genres;

}
