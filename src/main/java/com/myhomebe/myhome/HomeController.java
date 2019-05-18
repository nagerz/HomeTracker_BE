package com.myhomebe.myhome;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
  @RequestMapping("api/v1/")
  public String home() {
    return "Welcome to the MyHome backend";
  }
}
