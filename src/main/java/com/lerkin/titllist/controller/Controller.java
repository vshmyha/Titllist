package com.lerkin.titllist.controller;

import com.lerkin.titllist.controller.response.ResponseEntity;
import com.lerkin.titllist.controller.response.ResponseType;
import com.lerkin.titllist.controller.response.ResponseUtil;
import com.lerkin.titllist.controller.tool.ForwardUtil;
import com.lerkin.titllist.exception.SessionInvalidException;
import com.lerkin.titllist.exception.UserFriendlyException;
import com.lerkin.titllist.exception.UserIsBlockedException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {

    private static final String COMMAND = "command";
    private static final String UTF_8 = "UTF-8";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding(UTF_8);
            response.setContentType("application/json; charset=utf-8");
            String command = request.getParameter(COMMAND);
            TaskManager.impl(command, request, response);
        } catch (UserIsBlockedException e) {
            String url = ForwardUtil.createCommandPath(CommandName.GO_TO_BLOCKED_PAGE, null);
            ResponseEntity responseEntity = new ResponseEntity(ResponseType.NEW_PAGE, url);
            ResponseUtil.sendResponse(response, responseEntity);
        } catch (UserFriendlyException e) {
            ResponseUtil.sendResponse(response, e);
        } catch (SessionInvalidException e) {
            String url = ForwardUtil.createCommandPath(CommandName.GO_TO_START_PAGE, null);
            ResponseEntity responseEntity = new ResponseEntity(ResponseType.NEW_PAGE, url);
            ResponseUtil.sendResponse(response, responseEntity);
        } catch (Throwable e) {
            ResponseEntity responseEntity = new ResponseEntity(ResponseType.ERROR, "Server Error");
            ResponseUtil.sendResponse(response, responseEntity);
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doGet(req, resp);
    }


}
