package com.dg.ums.controllers;

import com.dg.ums.model.APIStatusResponse;
import com.dg.ums.model.DGUser;
import com.dg.ums.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private  UserService userService;

    @Autowired
    private HttpServletRequest request;

    public void displayHeaders(){
        request.getHeaderNames().asIterator().forEachRemaining(header->{
            System.out.println(header+"="+request.getHeader(header));
        });
    }
    @GetMapping
    public List<DGUser> users(){
        System.out.println("======================================");
        System.out.println("getAllUsers API is Called............");
        System.out.println("======================================");
        return userService.getAllUsers();
    }

    @PostMapping
    public DGUser addUser(@RequestBody DGUser newUser){
        System.out.println("======================================");
        System.out.println("addUser API is Called............");
        System.out.println("======================================");
        displayHeaders();
        return userService.addUser(newUser);
    }

    @PutMapping(path = "/{userId}")
    public DGUser updateUser(@PathVariable int userId, @RequestBody DGUser user){
        System.out.println("======================================");
        System.out.println("updateUser API is Called............");
        System.out.println("======================================");
       return userService.updateUser(userId, user);
    }

    @DeleteMapping(path = "/{userId}")
    public APIStatusResponse deleteUser(@PathVariable int userId){
        System.out.println("======================================");
        System.out.println("deleteUser API is Called............");
        System.out.println("======================================");
        return userService.deleteUser(userId);
    }

    @GetMapping(path="/{userId}")
    public DGUser getUserById(@PathVariable("userId") int id){
        System.out.println("======================================");
        System.out.println("getUserById API is Called............");
        System.out.println("======================================");
        displayHeaders();
        return  userService.getUserById(id);
    }

    @GetMapping(path = "/uname/{username}")
    public DGUser getUserByUsername(@PathVariable String username){
        System.out.println("======================================");
        System.out.println("getByUsername API is Called............");
        System.out.println("======================================");
        return userService.getUserByUsername(username);
    }
}
