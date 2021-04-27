package com.lerkin.titllist.controller.response;

import lombok.Data;

@Data
public class CustomResponse {
    private ResponseType status;
    private Object value;

    public CustomResponse(ResponseType status, Object value) {
        this.status = status;
        this.value = value;
    }

    public CustomResponse() {
    }
}
