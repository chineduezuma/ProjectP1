package com.revature.controllers;

import com.revature.models.User;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Combines @Controller and @ResponseBody
@RequestMapping("/users") // All HttP requests ending in /users will be sent
public class UserController {

    // We're going to use constructor injection to dependency inject the Service
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Insert a new User (any POST request ending in /users will invoke this method)
    @PostMapping
    public ResponseEntity<User> insertTeam(@RequestBody User user) {

        // Send user to the service which will send it to the DAO
        User newUser = userService.insertUser(user);

        //Send back the returned User object
        return ResponseEntity.ok(newUser);

        //ResponseEntity helps us build on HTTP Response
        //.ok() sets the status code to 200
        //we send the user object back in the response body
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){

        // This time, I will just do it in one line
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Integer> deleteUser(@PathVariable Integer userId){

        if(userService.getUser(userId) != null){
            userService.deleteUser(userId);
            return ResponseEntity.status(200).body(1);
        }
        return ResponseEntity.status(200).body(null);
    }


@PatchMapping("/{userId}")
public ResponseEntity<Integer> upgradeUser(@PathVariable Integer userId, @RequestBody User upgradeduser){
    User user = userService.upgradeUser(userId, upgradeduser);
    if(user == null){
        return ResponseEntity.status(400).body(0);
    }
    return ResponseEntity.status(200).body(1);
}


    //Exception Handlers--------------------------------

    //Spring MVC has a built-in Exception handler that cleans up our Controller methods

    //Exception handler for IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public  ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e){

        //If an IllegalArgument is thrown, send back a 400 (bad request)
        //with the Exception message in the response body
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
