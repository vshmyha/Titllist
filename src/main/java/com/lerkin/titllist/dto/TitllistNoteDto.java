package com.lerkin.titllist.dto;

import lombok.Value;

@Value
public class TitllistNoteDto {

	private AnimeDto anime;
	private Status status;
	private UserDto user;
}
