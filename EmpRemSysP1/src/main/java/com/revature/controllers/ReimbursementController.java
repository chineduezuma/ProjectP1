package com.revature.controllers;

import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Combines @Controller and @ResponseBody
@RequestMapping("/reimbursements") // All HttP requests ending in /users will be sent
@CrossOrigin(value = "http://localhost:5173", allowCredentials = "true") //Allows requests from any origin (including our frontend)
public class ReimbursementController {

    // We're going to use constructor injection to dependency inject the Service
    private final ReimbursementService reimbursementService;


    @Autowired
    public ReimbursementController(ReimbursementService reimbursementService) {
        this.reimbursementService = reimbursementService;
    }

    @GetMapping
    public ResponseEntity<List<Reimbursement>> getAllReimbursement(){

        // This time, I will just do it in one line
        return ResponseEntity.ok(reimbursementService.getAllReimbursements());
    }

    @GetMapping("/{status}/reimbursements")
    public ResponseEntity<List<Reimbursement>> getAllStatusReimbursements(@PathVariable String status){

        // This time, I will just do it in one line
        return ResponseEntity.ok(reimbursementService.getAllStatusReimbursements(status));
    }

    @PatchMapping("/{reimId}")
    public ResponseEntity<Reimbursement> resolveReimbursement(@PathVariable Integer reimId, @RequestBody Reimbursement resolvedreimbursement){
        Reimbursement reimbursement = reimbursementService.resolveReimbursement(reimId, resolvedreimbursement);
        if(reimbursement == null){
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.status(200).body(reimbursement);
    }


    @GetMapping("/{userId}/reimbursements")
    public ResponseEntity<List<Reimbursement>> getUserIdReimbursements(@PathVariable Integer userId){

        // This time, I will just do it in one line
        return ResponseEntity.ok(reimbursementService.getUserIdReimbursements(userId));
    }

    @GetMapping("/{userId}/{status}/reimbursements")
    public ResponseEntity<List<Reimbursement>> getUserIdPendingReimbursements(@PathVariable Integer userId, @PathVariable String status){

        // This time, I will just do it in one line
        return ResponseEntity.ok(reimbursementService.getUserIdPendingReimbursements(userId, status));
    }


    @PatchMapping("/reim/{reimId}")
    public ResponseEntity<Reimbursement> editDescriptionByReimbursementId(@PathVariable Integer reimId, @RequestBody Reimbursement resolvedreimbursement){
        Reimbursement reimbursement = reimbursementService.resolveReimbursement(reimId, resolvedreimbursement);
        if(reimbursement == null){
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.status(200).body(reimbursement);
    }



}
