package com.myhomebe.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
public class HomeController {

    @RequestMapping("/")
    public String welcome() {

        return "Welcome to the MyHome backend service";
    }
}
