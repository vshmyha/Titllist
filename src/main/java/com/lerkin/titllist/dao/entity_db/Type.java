package com.lerkin.titllist.dao.entity_db;

import com.fasterxml.jackson.annotation.JsonValue;
import com.lerkin.titllist.dao.entity.Status;

public enum Type {

	TV("TV Сериал"),
	ONA("ONA"),
	OVA("OVA"),
	FILM("Фильм"),
	CLIP("Клип");

	private String typeName;

	Type(String typeName) {
		this.typeName = typeName;
	}

	@JsonValue
	public String getTypeName() {
		return typeName;
	}

	public static Type byText(String text) {

		Type[] values = values();
		Type result = null;
		for (Type types : values) {
			if (text.equals(types.getTypeName())) {
				result = types;
				break;
			}
		}
		return result;
	}
}
