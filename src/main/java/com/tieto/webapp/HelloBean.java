package com.tieto.webapp;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Daniel Zvir on 20.07.2016.
 */
public class HelloBean {
    public List<Property> getHeaders(HttpServletRequest request) {
        return generateProperties(request.getHeaderNames(), request::getHeader);
    }

    public List<Property> getParameters(HttpServletRequest request) {
        return generateProperties(request.getParameterNames(), request::getParameter);
    }

    public List<Property> getAttributes(HttpServletRequest request) {
        return generateProperties(request.getAttributeNames(), key -> request.getAttribute(key).toString());
    }

    public List<Property> getSessionAttributes(HttpServletRequest request) {
        return generateProperties(request.getSession().getAttributeNames(), key -> request.getSession().toString());
    }

    private List<Property> generateProperties(Enumeration<String> keys, Function<String, String> valueGetter) {
        final List<Property> list = new ArrayList<>();
        while (keys.hasMoreElements()) {
            final String key = keys.nextElement();
            final String value = valueGetter.apply(key);
            final Property property = new Property(key, value);
            list.add(property);
        }
        return list;
    }

    public String getName(HttpServletRequest request) {
        return request.getParameter("name");
    }
}
