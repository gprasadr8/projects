package com.dg.tms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/todos")
public class TodoController {

    @GetMapping
    public String home(){
        return "Welcome to TMS";
    }
}
