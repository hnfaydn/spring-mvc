package com.example.springmvc;


import com.example.springmvc.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class Login {

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final ModelAndView modelAndView = new ModelAndView();

        final User user = new User();

        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));

        modelAndView.setViewName("display.jsp");
        modelAndView.addObject("result",user.getUsername());

        return modelAndView;
    }
}
