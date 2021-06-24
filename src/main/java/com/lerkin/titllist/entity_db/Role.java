package com.lerkin.titllist.entity_db;

import lombok.Getter;

@Getter
public enum Role {

    BLOCKED("Blocked"), SIMPLE("Simple"), ADMIN("Admin"), SUPER_ADMIN("Super Admin");

    private String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public boolean isBlocked() {
        return this.equals(BLOCKED);
    }

    public boolean isSimple() {
        return this.equals(SIMPLE);
    }

    public boolean isSuperAdmin() {return this.equals(SUPER_ADMIN); }

    public static Role byText(String text) {
        Role[] values = values();
        Role result = null;
        for (Role role : values) {
            if (text.equals(role.getRoleName())) {
                result = role;
                break;
            }
        }
        return result;
    }
}
