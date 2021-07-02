package com.lerkin.titllist.entity_db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAnime {

    private Anime anime;
    private Status status;
    private Integer userId;
}
