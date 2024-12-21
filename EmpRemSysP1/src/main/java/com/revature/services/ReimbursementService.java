package com.revature.services;

import com.revature.DAOs.ReimbursementDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service// 1 of the 4 stereotype annotations (makes a class a bean)
public class ReimbursementService {

    //Autowire the DAO
    private final ReimbursementDAO reimbursementDAO;

    @Autowired
    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }
}
