package com.tieto.webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Daniel Zvir on 21.07.2016.
 */
@WebServlet("/loginSystem/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String username = request.getParameter("username");
        if (username == null || username.isEmpty()) {
            throw new ServletException("Login must not be empty!");
        }
        final String password = request.getParameter("password");
        if (password == null || password.isEmpty()) {
            throw new ServletException("Password must not be empty!");
        }
        final UserService userService = new UserService();
        if (!userService.isValidLogin(username, password)) {
            throw new ServletException("Invalid login or password.");
        }


        final HttpSession session = request.getSession();
        Object destination = session.getAttribute("destination");
        session.setAttribute("destination", null);
        session.setAttribute("user", username);
        if (destination == null) {
            destination = (request.getContextPath() + "/welcome.html");
        }

        response.sendRedirect(destination.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
