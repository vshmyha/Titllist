package com.lerkin.titllist.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SomebodyToDieCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            PrintWriter printWriter = resp.getWriter();
            printWriter.write("body to die for");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
