package com.lerkin.titllist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lerkin.titllist.controller.response.CustomResponse;
import com.lerkin.titllist.controller.response.ResponseType;
import com.lerkin.titllist.controller.response.ResponseUtil;
import com.lerkin.titllist.exception.UserFriendlyException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Controller extends HttpServlet {

    private static final String COMMAND = "command";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String command = request.getParameter(COMMAND);
            TaskManager.impl(command, request, response);
        } catch (UserFriendlyException e) {
            String jsonResponse = ResponseUtil.createJSONResponse(e);
            ResponseUtil.sendResponse(response, jsonResponse);
        } catch (Throwable e) {
            CustomResponse customResponse = new CustomResponse(ResponseType.ERROR, "Server Error");
            String jsonResponse = ResponseUtil.createJSONResponse(customResponse);
            ResponseUtil.sendResponse(response, jsonResponse);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


}
