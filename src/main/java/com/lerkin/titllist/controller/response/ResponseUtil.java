package com.lerkin.titllist.controller.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lerkin.titllist.exception.UserFriendlyException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void sendResponse(HttpServletResponse resp, UserFriendlyException e) {
        try {
            String jsonResponse = createJSONResponse(e);
            PrintWriter writer = resp.getWriter();
            writer.write(jsonResponse);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void sendResponse(HttpServletResponse resp, ResponseEntity responseValue) {
        try {
            String jsonResponse = createJSONResponse(responseValue);
            PrintWriter writer = resp.getWriter();
            writer.write(jsonResponse);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static String createJSONResponse(UserFriendlyException e) throws JsonProcessingException {
        return createJSONResponse(new ResponseEntity(ResponseType.ERROR, e.getMessage()));
    }

    public static String createJSONResponse(ResponseType status, Object value) throws JsonProcessingException {
        return createJSONResponse(new ResponseEntity(status, value));
    }

    public static String createJSONResponse(ResponseEntity responseEntity) throws JsonProcessingException {
        return objectMapper.writeValueAsString(responseEntity);
    }
}
