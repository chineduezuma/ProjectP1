package com.revature.controllers;

import com.revature.models.DTOs.ReimbursementDTO;
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


    //MANAGER GET ALL REIMBURSEMENT
    @GetMapping
    public ResponseEntity<List<Reimbursement>> getAllReimbursement(){

        return ResponseEntity.ok(reimbursementService.getAllReimbursements());

    }




    //MANAGER GET ALL REIMBURSEMENT BY STATUS (PENDING)
    @GetMapping("/{status}")
    public ResponseEntity<List<Reimbursement>> getAllStatusReimbursements(@PathVariable String status){

        return ResponseEntity.ok(reimbursementService.getAllStatusReimbursements(status));

    }


    //MANAGER APPROVES OR DENIES A REIMBURSEMENT BY REIMID
    @PatchMapping("/{reimId}")
    public ResponseEntity<Reimbursement> resolveReimbursement(@PathVariable Integer reimId, @RequestBody Reimbursement resolvedreimbursement){

        Reimbursement reimbursement = reimbursementService.resolveReimbursement(reimId, resolvedreimbursement);

        if(reimbursement == null){

            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.status(200).body(reimbursement);
    }









    //USER / EMPLOYEE INSERT / CREATE REIMBURSEMENT
    @PostMapping
    public ResponseEntity<Reimbursement> insertReimbursement(@RequestBody ReimbursementDTO reimbursementDTO){

        return ResponseEntity.ok(reimbursementService.insertReimbursement(reimbursementDTO));

    }




    //USER / EMPLOYEE GET ALL REIMBURSEMENT BY USERID
    @GetMapping("/{userId}/reimbursements")
    public ResponseEntity<List<Reimbursement>> getUserIdReimbursements(@PathVariable Integer userId){

        return ResponseEntity.ok(reimbursementService.getUserIdReimbursements(userId));

    }




    //USER / EMPLOYEE SELECT ALL PENDING REIMBURSEMENT BY USERID AND STATUS
    @GetMapping("/{userId}/{status}/reimbursements")
    public ResponseEntity<List<Reimbursement>> getUserIdPendingReimbursements(@PathVariable Integer userId, @PathVariable String status){

        return ResponseEntity.ok(reimbursementService.getUserIdPendingReimbursements(userId, status));

    }




    //USER / EMPLOYEE EDIT DESCRIPTION OF PENDING REIMBURSEMENT BY REIMID
    @PatchMapping("/reim/{reimId}")
    public ResponseEntity<Reimbursement> editDescriptionByReimbursementId(@PathVariable Integer reimId, @RequestBody Reimbursement resolvedreimbursement){

        Reimbursement reimbursement = reimbursementService.editDescriptionByReimbursementId(reimId, resolvedreimbursement);

        if(reimbursement == null){

            return ResponseEntity.status(400).body(null);
        }

        return ResponseEntity.status(200).body(reimbursement);
    }



}
