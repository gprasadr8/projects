package com.dg.ums.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user/{id}/password")
public class PasswordController {

    @PostMapping(path = "reset-link")
    public String sendPasswordResetLink(@PathVariable int id){
        return "Password reset link is sent successfully..";
    }
    @PutMapping("/reset")
    public void resetPassword(@PathVariable int id){

    }

}
