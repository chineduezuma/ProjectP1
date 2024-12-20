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

    @Column(nullable = false)
    private String status = "pending";

    /* FK to User (every reimbursement belongs to a user - many reimbursements belong to one user)
    *
    *  fetch - defines when the Dependency is loaded
        * LAZY = loads dependency only when it's called
        * EAGER = loads dependency at runtime (preferred)
     * @JoinColumn - defines the column that will be used to link these tables in the DB
        * We have to provide the name of the PK in User */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId") // This links our FK to the PK in User (userId)
    private User user;

    // Boilerplate code----------------------------------no args, all args, getter/setter, toString


    public Reimbursement() {
    }

    public Reimbursement(int reimbId, String description, double amount, String status, User user) {
        this.reimbId = reimbId;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbId=" + reimbId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", user=" + user +
                '}';
    }
}
