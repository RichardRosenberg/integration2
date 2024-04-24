package com.group1.musicacademy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public ResponseEntity<String> home(){
        // The home page view goes here
        //return "index";
        return ResponseEntity.ok("Hello, This is homePage");
    }
    @GetMapping("/admin")
    public ResponseEntity<String> admin(){
        return ResponseEntity.ok("Hello, This is Admin page");
    }

//    @GetMapping("/login")
//    public String handleLogin(){
//        return "custom_login";
//    }

}
