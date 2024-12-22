package com.revature.services;

// The Service layer is where we have our business logic
// User input validation, Data manipulation/reformatting, User authentication, etc.

import com.revature.DAOs.UserDAO;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // 1 of the 4 stereotype annotations (makes a class a bean)
public class UserService {

    //Autowire the DAO
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }



    // This method inserts new User into the DB once they have been validated - CREATE AN ACCOUNT
    public User insertUser(User user){

        // Make sure the values are valid

        if (user.getFirstName() == null || user.getFirstName().isBlank()) {
            throw new IllegalArgumentException("User firstname can't be null or blank!");
        }

        if (user.getLastName() == null || user.getLastName().isBlank()) {
            throw new IllegalArgumentException("User lastname can't be null or blank!");
        }

        if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new IllegalArgumentException("User username can't be null or blank!");
        }

        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new IllegalArgumentException("User password can't be null or blank!");
        }

        // if none of these gets triggered, the User is valid and can be sent to the DAO

        return userDAO.save(user);

    }




    // MANAGER SELECTS  ALL USERS
    public List<User> getAllUsers() {

        return userDAO.findAll();

    }




    // MANAGER DELETES A USER
    public void deleteUser(Integer userId) {

        Optional<User> user = userDAO.findById(userId);

        if(user.isPresent()) {

            User deleteUser = user.get();

            userDAO.delete(deleteUser);

        }
    }





    public User getUser(Integer userId) {
        Optional<User> user = userDAO.findById(userId);
        return user.orElse(null);
    }




    public User upgradeUser(Integer userId, User upgradedUser) {
        Optional<User> user = userDAO.findById(userId);
        if(user.isPresent()) {
            User userNewRole = user.get();

            if(upgradedUser.getUserId() == userNewRole.getUserId()) {
                userNewRole.setRole("manager");
                return userDAO.save(userNewRole);
            }
        }
        return null;
    }

}
