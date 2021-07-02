package com.lerkin.titllist.entity_db;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {

    IN_PROCESS("In process"), DROPPED("Dropped"), COMPLETED("Completed"), PLANNED("Planned");

    private String statusName;

    Status(String statusName) {
        this.statusName = statusName;
    }

    @JsonValue
    public String getStatusName() {
        return statusName;
    }

    public static Status byText(String text) {
        Status[] values = values();
        Status result = null;
        for (Status status : values) {
            if (text.equals(status.getStatusName())) {
                result = status;
                break;
            }
        }
        return result;
    }
}
