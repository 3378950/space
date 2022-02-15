package com.zcs.space.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class DefailtController {

    @GetMapping
    public String sayHello() {
        return "欢迎进入Space";
    }
}
