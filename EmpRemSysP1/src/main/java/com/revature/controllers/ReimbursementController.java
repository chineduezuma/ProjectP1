package com.revature.controllers;

import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Combines @Controller and @ResponseBody
@RequestMapping("/reimbursements") // All HttP requests ending in /users will be sent
public class ReimbursementController {

    // We're going to use constructor injection to dependency inject the Service
    private final UserService ReimbursementService;


    @Autowired
    public ReimbursementController(UserService reimbursementService) {
        ReimbursementService = reimbursementService;
    }
}
