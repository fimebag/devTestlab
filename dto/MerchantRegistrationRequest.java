package com.fimepay.merchantapp.dto;

import java.util.List;

import com.fimepay.merchantapp.model.BusinessType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class MerchantRegistrationRequest {

    @NotBlank(message = "Business email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Business name is required")
    private String name;

    @NotBlank(message = "Business Registration Number is required")
    private String businessRegistrationNumber;

    @NotBlank(message = "Tax Identification Number (TIN) is required")
    private String taxIdentificationNumber;

    @NotBlank(message = "Business address is required")
    private String businessAddress;

    @NotBlank(message = "Nature of business is required")
    private String natureOfBusiness;


    public BusinessType getBusinessType() {
        return businessType;
    }
    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }
    @NotBlank(message = "Type of business is required")
    private BusinessType businessType;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank private String phoneNumber;
    
    private List<DirectorRequest> directors;  // Add this field!

    public List<DirectorRequest> getDirectors() {
        return directors;
    }
    public void setDirectors(List<DirectorRequest> directors) {
        this.directors = directors;
    }

    // Getters and Setters

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBusinessRegistrationNumber() {
        return businessRegistrationNumber;
    }
    public void setBusinessRegistrationNumber(String businessRegistrationNumber) {
        this.businessRegistrationNumber = businessRegistrationNumber;
    }
    public String getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }
    public void setTaxIdentificationNumber(String taxIdentificationNumber) {
        this.taxIdentificationNumber = taxIdentificationNumber;
    }
    public String getBusinessAddress() {
        return businessAddress;
    }
    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }
    public String getNatureOfBusiness() {
        return natureOfBusiness;
    }
    public void setNatureOfBusiness(String natureOfBusiness) {
        this.natureOfBusiness = natureOfBusiness;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
