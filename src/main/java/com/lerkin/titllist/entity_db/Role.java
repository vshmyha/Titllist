package com.lerkin.titllist.entity_db;

import lombok.Getter;

@Getter
public enum Role {

    BLOCKED("Blocked"), SIMPLE("Simple"), ADMIN("Admin"), SUPER_ADMIN("Super Admin");

    private String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }
}
