package com.fimepay.merchantapp.dto;

public class AcceptInvitationRequest {
    private String token;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;    
        // getter/setter

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

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
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}