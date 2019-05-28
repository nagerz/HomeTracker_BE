package com.myhomebe.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@CrossOrigin
@RestController
public class HomeController {

    @RequestMapping("/")
    public String welcome() {

        return "Welcome to the MyHome backend service";
    }
}
