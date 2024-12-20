package com.revature.controllers;

// TheController layer handles HTTP Requests (sends back HTTP responses)

import com.revature.models.User;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //Combines @Controller and @ResponseBody
@RequestMapping("/users") //All HTTP requests ending in /users will be sent here
public class UserController {

    // Using constructor injection to dependency inject the Service
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Insert a new User (any POST request ending in /users will invoke this method)
    @PostMapping
    public ResponseEntity<User> insertUser(@RequestBody User user) {

        // Send user to the service which send it to the DAO
        User newUser = userService.insertUser(user);

        // Send back the returned User object
        return ResponseEntity.ok(newUser);

        //ResponseEntity helps us build an HTTP Response
        //.ok() sets the status code to 200
        //we send the team object back in the response body
    }



    // Exception Handlers-----------------------------------------

    // Spring MVC has a built-in Exception handler that cleans up our Controller methods

    //Exception handler for IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {

        // If an IllegalArgument is thrown, send back a 400 (bad request)
        //with the Exception message in the response body
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
