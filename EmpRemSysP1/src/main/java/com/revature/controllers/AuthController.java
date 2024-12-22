package com.revature.controllers;

import com.revature.models.DTOs.LoginDTO;
import com.revature.models.DTOs.OutgoingUserDTO;
import com.revature.services.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(value = "http://localhost:5173", allowCredentials = "true")
public class AuthController {

    //autowire the service
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<OutgoingUserDTO> login(@RequestBody LoginDTO loginDTO, HttpSession session){

        //NOTE: we have an HTTP Session coming in via parameters, inherently included in HTTP requests

        //send the loginDTO to the service
        OutgoingUserDTO user = authService.login(loginDTO);

        System.out.println("User " + session.getAttribute("username") + " logged in!");

        //If we get here, login was successful and we can create the session!
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("username", user.getUsername());
        session.setAttribute("role", user.getRole());

        System.out.println("User " + session.getAttribute("username") + " logged in!");

        //Why store all this info in a session?

        /*
        -It lets us store User info that we can use to check if:
            -they're logged in
            -their role is appropriate for the functionality they want to access
            -personalize the app (use their name in the UI, etc.)
            -simplify our URLs!
                ex: use the stored userId in "findXByUserId" methods
                instead of passing it in the path variable
         */

        //Return the user info to the client
        return ResponseEntity.ok(user);

    }

}
