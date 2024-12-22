package com.revature.models.DTOs;

public class IncomingUserDTO {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    private int reimbId;

    //boilerplate-----------------------

    public IncomingUserDTO() {
    }

    public IncomingUserDTO(String username, String password, String firstName, String lastName, String role, int reimbId) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.reimbId = reimbId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getReimbursementId() {
        return reimbId;
    }

    public void setReimbursementId(int reimbursementId) {
        this.reimbId = reimbId;
    }

    @Override
    public String toString() {
        return "IncomingUserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", reimbursementId=" + reimbId +
                '}';
    }
}
