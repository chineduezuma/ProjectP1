package com.revature.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component // 1 of the 4 stereotype annotations (makes the class a Bean)
@Entity // This annotation makes a DB table based on this Class
@Table(name = "reimbursements") //This annotation lets us specify properties (like table name)
public class Reimbursement {

    @Id // This annotation makes the field a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This makes our PK auto-increment integers
    private int reimbId;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false) // This column Must have a value on insert
    private String status = "pending";

    // Boilerplate code----------------------------------no args, all args, getter/setter, toString


    public Reimbursement() {
    }

    public Reimbursement(int reimbId, String description, double amount, String status) {
        this.reimbId = reimbId;
        this.description = description;
        this.amount = amount;
        this.status = status;
    }

    public int getReimbId() {
        return reimbId;
    }

    public void setReimbId(int reimbId) {
        this.reimbId = reimbId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbId=" + reimbId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }
}
