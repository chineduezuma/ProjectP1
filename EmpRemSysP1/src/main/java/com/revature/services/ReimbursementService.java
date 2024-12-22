package com.revature.services;

import com.revature.DAOs.ReimbursementDAO;
import com.revature.DAOs.UserDAO;
import com.revature.models.DTOs.ReimbursementDTO;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service// 1 of the 4 stereotype annotations (makes a class a bean)
public class ReimbursementService {

    //Autowire the DAO
    private final ReimbursementDAO reimbursementDAO;
    private final UserDAO userDAO;

    @Autowired
    public ReimbursementService(ReimbursementDAO reimbursementDAO, UserDAO userDAO) {
        this.reimbursementDAO = reimbursementDAO;
        this.userDAO = userDAO;
    }



    //MANAGER GET ALL REIMBURSEMENT
    public List<Reimbursement> getAllReimbursements() {

        return reimbursementDAO.findAll();

    }




    //MANAGER GET ALL REIMBURSEMENT BY STATUS (PENDING)
    public List<Reimbursement> getAllStatusReimbursements(String status) {

        if (status == null || status.isBlank()) {

            throw new IllegalArgumentException("status can't be null or blank!");
        }

        if(status.equalsIgnoreCase("approve")){

            return reimbursementDAO.findByStatus(status);

        } else if (status.equalsIgnoreCase("deny")) {

            return reimbursementDAO.findByStatus(status);

        }else {

            return  reimbursementDAO.findByStatus(status);
        }

    }




//    //MANAGER APPROVES OR DENIES A REIMBURSEMENT BY REIMID
//    public Reimbursement resolveReimbursement(Integer reimId, Reimbursement resolvedreimbursement) {
//
//        Optional<Reimbursement> reimbursement = reimbursementDAO.findByReimId(reimId)
//
//            Reimbursement newReimbursementStatus = reimbursement.get();
//
//            if(resolvedreimbursement.getReimbId() == newReimbursementStatus.getReimbId()) {
//
//                newReimbursementStatus.setStatus(resolvedreimbursement.getStatus());
//
//                return reimbursementDAO.save(newReimbursementStatus);
//            }
//            return null;
//    }




    //USER / EMPLOYEE INSERT / CREATE REIMBURSEMENT
    public Reimbursement insertReimbursement(ReimbursementDTO reimbursementDTO){

        // Make sure the values are valid
        if (reimbursementDTO.description() == null || reimbursementDTO.description().isBlank()) {
            throw new IllegalArgumentException("Reimbursement description can't be null or blank!");
        }

        if (reimbursementDTO.amount() <= 0) {
            throw new IllegalArgumentException("Reimbursement amount can't be less than 1!");
        }

        Optional<User> user = userDAO.findById(reimbursementDTO.userId());
        if(user.isEmpty()){
            throw new RuntimeException("User firstname can't be empty!");
        }

        Reimbursement reimbursement = new Reimbursement(

        );

        reimbursement.setDescription(reimbursementDTO.description());
        reimbursement.setAmount(reimbursementDTO.amount());
        reimbursement.setStatus("pending");
        reimbursement.setUser(user.get());


        return reimbursementDAO.save(reimbursement);

    }




    //USER / EMPLOYEE SELECT ALL REIMBURSEMENT BY USERID
    public List<Reimbursement> getUserIdReimbursements(Integer userId) {

        if (userId == null || userId == 0) {
            throw new IllegalArgumentException("userId can't be null or blank!");
        }

        return  reimbursementDAO.findByUser_UserId(userId);

    }




    //USER / EMPLOYEE SELECT ALL PENDING REIMBURSEMENT BY USERID AND STATUS
    public List<Reimbursement> getUserIdPendingReimbursements(Integer userId, String status) {

        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("status can't be null or blank!");
        }

        if (userId == null || userId == 0) {
            throw new IllegalArgumentException("userId can't be null or blank!");
        }

        return  reimbursementDAO.findByUser_UserIdAndStatus(userId, status);

    }




    //USER / EMPLOYEE EDIT DESCRIPTION OF PENDING REIMBURSEMENT BY REIMID
    public Reimbursement editDescriptionByReimbursementId(Integer reimId, Reimbursement newMessage){

        Optional<Reimbursement> message = reimbursementDAO.findById(reimId);

        if(message.isPresent()){
            Reimbursement originalMessage = message.get();

            if(newMessage.getDescription() != null || !(newMessage.getDescription().isBlank())){
                originalMessage.setDescription(newMessage.getDescription());
                return reimbursementDAO.save(originalMessage);
            }
        }
        return null;
    }



}
