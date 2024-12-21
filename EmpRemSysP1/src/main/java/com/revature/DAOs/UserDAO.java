package com.revature.DAOs;

import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Extending JpaRepository gives us access to basic CRUD methods that we don't have to write
    //This includes find all, insert, find by id, update, delete

//JpaRepository takes two generics:
    //-The type of the Entity we're working with
    //-The type of the primary key of that Entity

@Repository //1 of the 4 stereotype annotations (makes a class a bean)
public interface UserDAO extends JpaRepository<User, Integer> {


}
