package com.lerkin.titllist.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre extends DBEntity {

    private String genreName;

    public Genre(Integer id, String genreName) {
        super(id);
        this.genreName = genreName;
    }
}
