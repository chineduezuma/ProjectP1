package com.revature.DAOs;

import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthDAO extends JpaRepository<User, Integer> {

    //I like to have a layer just for Auth stuff
    //In a personal project, I would probably put register in this layer too

    //For login, we can check if an inputted username/password matches a record in the DB
    //If it comes back empty, the user doesn't exist and login fails
    //If it returns something, we can save that info as the logged-in user
    User findByUsernameAndPassword(String username, String password);
}
