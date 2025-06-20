package com.fimepay.merchantapp.dto;

// src/main/java/com/fimepay/merchantapp/dto/MerchantProfileUpdateRequest.java

import jakarta.validation.constraints.*;

public class MerchantProfileUpdateRequest {

    @Size(max = 1000)
    private String businessDescription;

    private String contactPersonName;
    private String contactPersonPosition;

    @Email
    private String contactEmail;

    private String contactPhone;

    // Getters & setters

    public String getBusinessDescription() {
        return businessDescription;
    }
    public void setBusinessDescription(String businessDescription) {
        this.businessDescription = businessDescription;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }
    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactPersonPosition() {
        return contactPersonPosition;
    }
    public void setContactPersonPosition(String contactPersonPosition) {
        this.contactPersonPosition = contactPersonPosition;
    }

    public String getContactEmail() {
        return contactEmail;
    }
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}
