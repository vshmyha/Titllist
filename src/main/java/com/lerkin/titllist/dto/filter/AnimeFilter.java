package com.lerkin.titllist.dto.filter;

import lombok.Value;

@Value
public class AnimeFilter {

	String type;
	Integer genreId;
	Short releaseDate;
}
