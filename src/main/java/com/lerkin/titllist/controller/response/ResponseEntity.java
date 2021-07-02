package com.lerkin.titllist.controller.response;

import lombok.Data;

@Data
public class ResponseEntity {
    private ResponseType status;
    private Object value;

    public ResponseEntity(ResponseType status, Object value) {
        this.status = status;
        this.value = value;
    }

    public ResponseEntity(ResponseType status) {
        this.status = status;
    }

    public ResponseEntity() {
    }
}
