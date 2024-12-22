package com.revature.services;

import com.revature.DAOs.AuthDAO;
import com.revature.controllers.AuthController;
import com.revature.models.DTOs.LoginDTO;
import com.revature.models.DTOs.OutgoingUserDTO;
import com.revature.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    //autowire the DAO
    private final AuthDAO authDAO;

    @Autowired
    public AuthService(AuthDAO authDAO){
        this.authDAO = authDAO;
    }

    //method that takes in a username/password and returns the matching User if found
    //AKA a login method
    public OutgoingUserDTO login(LoginDTO loginDTO){

        //TODO: validate the loginDTO fields

        //Use the DTO to find a User in the DB
        User u = authDAO.findByUsernameAndPassword(
                loginDTO.getUsername(), loginDTO.getPassword());

        //If no User is found, throw an exception
        if(u == null){
            //TODO: we could have made a custom "LoginFailedException"
            throw new IllegalArgumentException("No user found with those credentials");
        }

        //Return an OutUserDTO to the controller
        return new OutgoingUserDTO(
                u.getUserId(), u.getUsername(), u.getRole(), u.getFirstName(), u.getLastName());

    }
}
