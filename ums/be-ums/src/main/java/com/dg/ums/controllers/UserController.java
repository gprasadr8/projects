package com.dg.ums.controllers;

import com.dg.ums.model.DGUser;
import com.dg.ums.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private  UserService userService;
    @GetMapping
    public List<DGUser> users(){
        return userService.getAllUsers();
    }

    @PostMapping
    public DGUser addUser(@RequestBody DGUser newUser){
        return userService.addUser(newUser);
    }

    @PutMapping
    public DGUser updateUser(){
        return new DGUser();
    }

    @DeleteMapping(path = "/{id}")
    public DGUser deleteUser(@PathVariable int id){
        return new DGUser();
    }

    @GetMapping(path="/{id}")
    public DGUser getUserById(@PathVariable("id") int id){
        return  userService.getUserById(id);
    }

    @GetMapping(path = "/uname/{username}")
    public DGUser getUserByUsername(@PathVariable String username){
        return new DGUser();
    }
}
