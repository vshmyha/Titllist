package com.lerkin.titllist.entity_db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre extends DBEntity {

    private String genreName;
}
