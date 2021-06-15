package com.lerkin.titllist.controller.tool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForwardUtil {
    private static final String START_PAGE = "WEB-INF/jsp/start.jsp";
    private static final String MAIN_PAGE = "WEB-INF/jsp/main.jsp";
    private static final String REGISTRATION_PAGE = "WEB-INF/jsp/registration.jsp";
    private static final String BLOCKED_PAGE = "WEB-INF/jsp/blocked_page.jsp";

    public static void sendRedirect(HttpServletResponse response, String command, String attributes) {
        String url = createCommandPath(command, attributes);
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static String createCommandPath(String command, String attributes) {
        String urlCommand = "controller?command=";
        StringBuilder builder = new StringBuilder();
        builder.append(urlCommand);
        builder.append(command);
        if (attributes != null) {
            builder.append(attributes);
        }
        String url = builder.toString();
        return url;

    }

    public static void forwardToRegistrationPage(ServletRequest request, ServletResponse response) {
        forward(request, response, REGISTRATION_PAGE);
    }

    public static void forwardToStartPage(ServletRequest request, ServletResponse response) {
        forward(request, response, START_PAGE);
    }

    public static void forwardToMainPage(ServletRequest request, ServletResponse response) {
        forward(request, response, MAIN_PAGE);
    }

    public static void forwardToBlockedPage(ServletRequest request, ServletResponse response){
        forward(request, response, BLOCKED_PAGE);
    }

    private static void forward(ServletRequest request, ServletResponse response, String page) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
