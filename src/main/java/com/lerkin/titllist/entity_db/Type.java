package com.lerkin.titllist.entity_db;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type extends DBEntity {

    private String typeName;
}
