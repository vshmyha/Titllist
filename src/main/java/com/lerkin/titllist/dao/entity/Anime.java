package com.lerkin.titllist.dao.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Anime extends DBEntity {
    private String rusName;
    private String japName;
    private Type type;
    private Integer episodes;
    private Integer duration;
    private Short releaseDate;
    private List<Genre> genres;

    public Anime(String rusName, String japName) {
        this.rusName = rusName;
        this.japName = japName;
    }

    public Anime(String rusName, String japName, Type type, Integer episodes, Integer duration, Short releaseDate) {
        this.rusName = rusName;
        this.japName = japName;
        this.type = type;
        this.episodes = episodes;
        this.duration = duration;
        this.releaseDate = releaseDate;
    }
}
