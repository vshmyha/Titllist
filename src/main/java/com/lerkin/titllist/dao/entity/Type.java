package com.lerkin.titllist.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type extends DBEntity {

	private String typeName;

	public Type(Integer id, String typeName) {

		super(id);
		this.typeName = typeName;
	}
}
