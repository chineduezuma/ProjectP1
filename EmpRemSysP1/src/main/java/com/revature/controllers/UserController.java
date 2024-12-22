package com.revature.controllers;


import com.revature.models.User;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Combines @Controller and @ResponseBody
@RequestMapping("/users") // All HttP requests ending in /users will be sent
@CrossOrigin(value = "http://localhost:5173", allowCredentials = "true") //Allows requests from any origin (including our frontend)
public class UserController {

    // We're going to use constructor injection to dependency inject the Service
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Insert a new User (any POST request ending in /users will invoke this method) - CREATE AN ACCOUNT
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




    // MANAGER SELECTS  ALL USERS
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){

        return ResponseEntity.ok(userService.getAllUsers());

    }



    // MANAGER DELETES A USER
    @DeleteMapping("/{userId}")
    public ResponseEntity<Integer> deleteUser(@PathVariable Integer userId){

        if(userService.getUser(userId) != null){

            userService.deleteUser(userId);

            return ResponseEntity.status(200).body(1);
        }

        return ResponseEntity.status(200).body(null);
    }




    // MANAGER PROMOTES A USER TO MANAGER
    @PatchMapping("/{userId}")
    public ResponseEntity<User> upgradeUser(@PathVariable Integer userId, @RequestBody User upgradeduser){

        User user = userService.upgradeUser(userId, upgradeduser);

        if(user == null){

            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.status(200).body(user);
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
