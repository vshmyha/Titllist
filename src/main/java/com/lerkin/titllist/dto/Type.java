package com.lerkin.titllist.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.lerkin.titllist.exception.InternalTitllistException;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum Type {

	TV("TV Сериал"),
	ONA("ONA"),
	OVA("OVA"),
	FILM("Фильм"),
	SPECIAL("Спешл"),
	CLIP("Клип");

	private final String typeName;

	@JsonValue
	public String getTypeName() {
		return typeName;
	}

	@JsonCreator
	public static Type byText(String text) {

		return Arrays.stream(values()).filter(value -> value.getTypeName().equals(text))
				.findFirst()
				.orElseThrow(() -> new InternalTitllistException("Unknown type " + text + " for Type enum"));
	}
}
