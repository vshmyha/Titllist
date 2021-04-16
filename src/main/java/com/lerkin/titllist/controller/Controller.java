package com.lerkin.titllist.controller;

import com.lerkin.titllist.exception.UserFriendlyException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final String COMMAND = "command";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String command = req.getParameter(COMMAND);
            TaskManager.impl(command, req, resp);
        } catch (UserFriendlyException e) {
            //message for user
        } catch (Exception e) {
            e.printStackTrace();
        }
//        PrintWriter writer = resp.getWriter();
//        if ("someBody".equals(command)) {
//            writer.write("body once told me");
//        } else {
//            writer.write("body to die for");
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


}
