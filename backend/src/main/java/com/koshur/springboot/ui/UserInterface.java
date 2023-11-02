package com.koshur.springboot.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserInterface {
    @GetMapping("/ui")
    public String index(){
        return "index";
    }
}
