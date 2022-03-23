package com.adilmx.spring_mvc_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class SpringSecurityController {

    @GetMapping("notAuthorized")
    public String authorizationError(){
        return "notAuthorized";
    }

    @GetMapping("login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("logout")
    public String loginPage(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/login";
    }
}
