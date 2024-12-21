package com.revature.DAOs;

//Extending JpaRepository gives us access to basic CRUD methods that we don't have to write
    //This includes find all, insert, find by id, update, delete

import com.revature.models.Reimbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//JpaRepository takes two generics:
    //-The type of the Entity we're working with
    //-The type of the primary key of that Entity
@Repository //1 of the 4 stereotype annotation (makes a class a bean)
public interface ReimbursementDAO extends JpaRepository<Reimbursement, Integer> {



}
