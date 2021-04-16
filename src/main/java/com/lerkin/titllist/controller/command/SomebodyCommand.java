package com.lerkin.titllist.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SomebodyCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            PrintWriter writer = resp.getWriter();
            writer.write("body once told me");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
