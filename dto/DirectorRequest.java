package com.fimepay.merchantapp.dto;

import com.fimepay.merchantapp.model.IDType;
import java.time.LocalDate;
import java.util.UUID;

public class DirectorRequest {
    private String legalName;
    private LocalDate dob;
    private String nationality;
    private String role;
    private String bvn;
    private String serviceAgreement;
    private IDType idType;
    public String getLegalName() {
        return legalName;
    }
    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getBvn() {
        return bvn;
    }
    public void setBvn(String bvn) {
        this.bvn = bvn;
    }
    public String getServiceAgreement() {
        return serviceAgreement;
    }
    public void setServiceAgreement(String serviceAgreement) {
        this.serviceAgreement = serviceAgreement;
    }
    public IDType getIdType() {
        return idType;
    }
    public void setIdType(IDType idType) {
        this.idType = idType;
    }

}
