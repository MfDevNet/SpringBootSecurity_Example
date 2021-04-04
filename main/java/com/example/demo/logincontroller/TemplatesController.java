package com.example.demo.logincontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplatesController {

    @GetMapping("login")
    public String getLogin(){
        return "login";
    }

    @GetMapping("courses")
    public String getCourses(){
        return "courses";
    }


}
