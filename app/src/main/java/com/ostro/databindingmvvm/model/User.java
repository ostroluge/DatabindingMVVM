package com.ostro.databindingmvvm.model;

/**
 * Created by Thomas Ostrowski
 * ostrowski.thomas@gmail.com
 */

public class User {

    private String username;
    private String firstName;
    private String lastName;
    private String password;

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
