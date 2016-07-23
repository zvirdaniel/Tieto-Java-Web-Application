package com.tieto.webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.function.Function;

/**
 * Created by Daniel Zvir on 19.07.2016.
 */
@WebServlet("/helloServlet")
public class HelloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final PrintWriter writer = response.getWriter();
        writer.println("POST Hello Daniel.");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final PrintWriter writer = response.getWriter();

        writer.println("<!DOCTYPE html>");
        writer.println("<html><head><title>WebApp</title></head><body>");
        writer.println("GET method.<br>");
        writer.println("Hello, " + getName(request));

        // Method reference, even simpler lambda
        final Enumeration<String> headerNames = request.getHeaderNames();
        printProperties("Headers:", headerNames, request::getHeader, writer);

        final Enumeration<String> parameterNames = request.getParameterNames();
        printProperties("Parameters:", parameterNames, request::getParameter, writer);

        // Lambda expression allows me to use methods like toString
        final Enumeration<String> attributes = request.getAttributeNames();
        printProperties("Attributes:", attributes, key -> request.getAttribute(key).toString(), writer);

        writer.print("</body></html>");

    }

    private void printProperties(String title, Enumeration<String> keys, Function<String, String> valueGetter, PrintWriter writer) {
        writer.print("<h2>" + title + "</h2>");
        writer.println("<table>");
        while (keys.hasMoreElements()) {
            final String key = keys.nextElement();
            final String value = valueGetter.apply(key);
            writer.print("<tr><td>");
            writer.print(key + ":");
            writer.print("</td><td>");
            writer.println(value);
            writer.println("</td></tr>");
        }
        writer.println("</table>");
    }

    private String getName(HttpServletRequest request) {
        String result = request.getParameter("name");
        if (result == null) {
            return "default";
        }
        return result;
    }
}
