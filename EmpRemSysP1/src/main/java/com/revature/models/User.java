package com.revature.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component // 1 of the 4 stereotype annotations (makes the class a Bean)
@Entity // This annotation makes a DB table based on this Class
@Table(name = "users") //This annotation lets us specify properties (like table name)
public class User {

    @Id // This annotation makes the field a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This makes our PK auto-increment integers
    private int userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    // @Column isn't necessary UNLESS you want to set DB column name or constraints

    @Column(nullable = false) // This column Must have a value on insert
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role = "employee";  // Every new user will be an employee by default

    // Boilerplate code----------------------------------no args, all args, getter/setter, toString

    public User() {
    }

    public User(int userId, String firstName, String lastName, String username, String password, String role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
