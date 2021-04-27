package com.lerkin.titllist.controller.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lerkin.titllist.exception.UserFriendlyException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void sendResponse(HttpServletResponse resp, String responseValue) throws JsonProcessingException {
        try {
            PrintWriter writer = resp.getWriter();
            writer.write(responseValue);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static String createJSONResponse(UserFriendlyException e) throws JsonProcessingException {
        return createJSONResponse(new CustomResponse(ResponseType.ERROR, e.getMessage()));
    }

    public static String createJSONResponse(ResponseType status, Object value) throws JsonProcessingException {
        return createJSONResponse(new CustomResponse(status, value));
    }

    public static String createJSONResponse(CustomResponse customResponse) throws JsonProcessingException {
        return objectMapper.writeValueAsString(customResponse);
    }
}
