package com.lerkin.titllist.controller.tool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class ForwardUtil {
    private static final String START_PAGE = "WEB-INF/jsp/start.jsp";
    private static final String MAIN_PAGE = "WEB-INF/jsp/main.jsp";

    public static void forwardToStartPage(ServletRequest request, ServletResponse response){
       forward(request, response, START_PAGE);
    }

    public static void forwardGoToMainPage(ServletRequest request, ServletResponse response){
        forward(request, response, MAIN_PAGE);
    }

    private static void forward(ServletRequest request, ServletResponse response, String page){
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
