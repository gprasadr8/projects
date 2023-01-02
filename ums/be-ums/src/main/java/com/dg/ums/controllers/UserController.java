package com.dg.ums.controllers;

import com.dg.ums.model.DGUser;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public List<DGUser> users(){
        return Collections.emptyList();
    }

    @PostMapping
    public DGUser addUser(){
        return new DGUser();
    }

    @PutMapping
    public DGUser updateUser(){
        return new DGUser();
    }

    @DeleteMapping(path = "/{id}")
    public DGUser deleteUser(){
        return new DGUser();
    }

    @GetMapping(path="/{id}")
    public DGUser getUserById(@PathVariable("id") String userId){
      return new DGUser();
    }

    @GetMapping(path = "/uname/{username}")
    public DGUser getUserByUsername(@PathVariable String username){
        return new DGUser();
    }
}
