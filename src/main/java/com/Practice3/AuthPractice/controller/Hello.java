package com.Practice3.AuthPractice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/public")
public class Hello {

    @GetMapping("/hello")
    public String hello(){
        return "Welocome!";
    }
    @PostMapping("/hello")
    public String helloPost(@RequestBody String name){
        return "Hello" + name + "!";
    }
}
