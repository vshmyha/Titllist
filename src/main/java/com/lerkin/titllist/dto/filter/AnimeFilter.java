package com.lerkin.titllist.dto.filter;

import com.lerkin.titllist.dto.Type;
import lombok.Value;

@Value
public class AnimeFilter {

	String type;
	Integer genreId;
	Short releaseDate;
}
