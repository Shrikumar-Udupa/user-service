package com.userservice.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.userservice.entity.User;
import com.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Void> cretaeUser(@RequestBody User user) {
        userService.cretaeUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
//  useer details
    @GetMapping("/get/{id}")
    private User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

//  get all orders for a user
    @GetMapping("/orders/{userId}")
    public JsonNode getAllOrdersForUser(@PathVariable Long userId) {
        return  userService.getAllOrdersForUser(userId);
    }

}
