package com.fimepay.merchantapp.dto;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchantDTO {

    private UUID id;
    private String email;
    private String name;
    private String businessRegistrationNumber;
    private String taxIdentificationNumber;
    private String businessAddress;
    private String natureOfBusiness;
    private String businessType;
    private boolean emailVerified;
    private boolean phoneVerified;
    private boolean kycVerified;

    private String companyLogo;
    private String businessDescription;
    private String contactPersonName;
    private String contactPersonPosition;
    private String contactEmail;
    private String contactPhone;

    // Getters and Setters

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
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
    public String getBusinessType() {
        return businessType;
    }
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
    public boolean isEmailVerified() {
        return emailVerified;
    }
    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }
    public boolean isPhoneVerified() {
        return phoneVerified;
    }
    public void setPhoneVerified(boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }
    public boolean isKycVerified() {
        return kycVerified;
    }
    public void setKycVerified(boolean kycVerified) {
        this.kycVerified = kycVerified;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }
    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

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
