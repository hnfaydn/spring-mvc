package com.example.springmvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@Component
public class LogFilter implements Filter {
    ServletContext servletContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        servletContext = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String logMessage = logMessage(httpServletRequest);

        servletContext.log(String.valueOf(logMessage));

        chain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {
    }

    private String logMessage(HttpServletRequest httpServletRequest) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Request: {" + httpServletRequest.getMethod() + " ");

        ArrayList<String> list = Collections.list(httpServletRequest.getParameterNames());
        stringBuilder.append("[{");
        for (String s : list) {
            stringBuilder.append(s + ": " + httpServletRequest.getParameter(s) + ", ");
        }
        StringBuilder stringBuilderSubString =
                new StringBuilder(stringBuilder.substring(0, stringBuilder.lastIndexOf(",")));

        stringBuilderSubString.append("}]}");

        return String.valueOf(stringBuilderSubString);
    }
}


