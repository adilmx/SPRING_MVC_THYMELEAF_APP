package com.adilmx.spring_mvc_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorsController {

    @GetMapping("notAuthorized")
    public String authorizationError(){
        return "notAuthorized";
    }
}
