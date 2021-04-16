package com.lerkin.titllist.entity_db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends DBEntity {

    private String userName;
    private String password;
    private Role role;
}
