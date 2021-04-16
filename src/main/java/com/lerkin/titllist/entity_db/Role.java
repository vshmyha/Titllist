package com.lerkin.titllist.entity_db;

import lombok.Getter;

@Getter
public enum Role {

    BLOCKED(0), SIMPLE(1), ADMIN(2), SUPER_ADMIN(3);

    private int role;

    Role(int role) {
        this.role = role;
    }
}
