package com.dg.ums.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user/{id}/password")
public class PasswordController {

    @PostMapping(path = "reset-link")
    public String sendPasswordResetLink(){
        return "Password reset link is sent successfully..";
    }
    @PutMapping("/reset")
    public void resetPassword(){

    }

}
