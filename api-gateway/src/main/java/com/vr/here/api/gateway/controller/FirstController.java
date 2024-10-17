package com.vr.here.api.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    @GetMapping("/helloworld")
    public String helloWorld() {
        return "Hello World";
    }
}
