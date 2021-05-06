package com.lerkin.titllist.entity_db;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Anime extends DBEntity{
    private String rusName;
    private String japName;
    private Type type;
    private Integer episodesCount;
    private Integer duration;
    private Short release_date;
    private List<String> genres;

}
