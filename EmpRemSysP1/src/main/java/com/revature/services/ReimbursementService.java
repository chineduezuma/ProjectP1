package com.revature.services;

import com.revature.DAOs.ReimbursementDAO;
import com.revature.models.Reimbursement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service// 1 of the 4 stereotype annotations (makes a class a bean)
public class ReimbursementService {

    //Autowire the DAO
    private final ReimbursementDAO reimbursementDAO;

    @Autowired
    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }

    public Reimbursement insertReimbursement(Reimbursement reimbursement){

        // Make sure the values are valid
        if (reimbursement.getDescription() == null || reimbursement.getDescription().isBlank()) {
            throw new IllegalArgumentException("User firstname can't be null or blank!");
        }

        if (reimbursement.getAmount() == 0) {
            throw new IllegalArgumentException("User firstname can't be null or blank!");
        }

        return reimbursementDAO.save(reimbursement);

    }

    // This method gets all reimbursement from the DB
    public List<Reimbursement> getAllReimbursements() {


        return reimbursementDAO.findAll();
    }

    // This method gets all pending reimbursement from the DB
    public List<Reimbursement> getAllStatusReimbursements(String status) {

        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("status can't be null or blank!");
        }

        if(status.equalsIgnoreCase("approve")){
            return reimbursementDAO.findByReimbursementStatus(status);
        } else if (status.equalsIgnoreCase("deny")) {
            return reimbursementDAO.findByReimbursementStatus(status);
        }else {
            return  reimbursementDAO.findByReimbursementStatus(status);
        }

    }

    public Reimbursement resolveReimbursement(Integer reimId, Reimbursement resolvedreimbursement) {
        Optional<Reimbursement> reimbursement = reimbursementDAO.findById(reimId);
        if(reimbursement.isPresent()) {
            Reimbursement newReimbursementStatus = reimbursement.get();

            if(resolvedreimbursement.getReimbId() == newReimbursementStatus.getReimbId()) {

                return reimbursementDAO.save(newReimbursementStatus);
            }
        }
        return null;
    }


    public List<Reimbursement> getUserIdReimbursements(Integer userId) {

        if (userId == null || userId == 0) {
            throw new IllegalArgumentException("userId can't be null or blank!");
        }

        return  reimbursementDAO.findByReimbursementUserId(userId);

    }

    public List<Reimbursement> getUserIdPendingReimbursements(Integer userId, String status) {

        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("status can't be null or blank!");
        }

        if (userId == null || userId == 0) {
            throw new IllegalArgumentException("userId can't be null or blank!");
        }

        return  reimbursementDAO.findByReimbursementUserIdAndStatus(userId, status);

    }

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
