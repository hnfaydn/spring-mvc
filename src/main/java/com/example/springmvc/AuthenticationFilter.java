package com.example.springmvc;

import com.example.springmvc.exception.PasswordIsNotCorrectException;
import com.example.springmvc.exception.UsernameIsNotCorrectException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    RequestDispatcher dispatcher;

    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;

    String userName = httpServletRequest.getParameter("username");
    String password = httpServletRequest.getParameter("password");
    try {
      userNameCheck(userName);
      passwordCheck(password);
    } catch (UsernameIsNotCorrectException e) {
      httpServletRequest.setAttribute("usernameMessage", e.getMessage());
      dispatcher = httpServletRequest.getServletContext().getRequestDispatcher("/errorUsername.jsp");
      dispatcher.forward(request, response);
    } catch (PasswordIsNotCorrectException e) {
      httpServletRequest.setAttribute("passwordMessage", e.getMessage());
      dispatcher = httpServletRequest.getServletContext().getRequestDispatcher("/errorPassword.jsp");
      dispatcher.forward(request, response);
    }
    chain.doFilter(httpServletRequest,httpServletResponse);
  }

  private void userNameCheck(String userName) throws UsernameIsNotCorrectException {
    if (!"admin".equals(userName)) {
      throw new UsernameIsNotCorrectException("Name does not match!");
    }
  }

  private void passwordCheck(String password)
      throws PasswordIsNotCorrectException {
    if (!"atmosware".equals(password)) {
      throw new PasswordIsNotCorrectException("Password is not correct!");
    }
  }

  @Override
  public void destroy() {}
}
