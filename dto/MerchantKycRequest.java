package com.fimepay.merchantapp.dto;

import jakarta.validation.constraints.NotBlank;

public class MerchantKycRequest {

    @NotBlank(message = "Certificate of Incorporation is required")
    private String certificateOfIncorporation;

    @NotBlank(message = "TIN document is required")
    private String tinDocument;

    @NotBlank(message = "Utility bill is required")
    private String utilityBill;

    @NotBlank(message = "Authorized signatory ID is required")
    private String authorizedSignatoryId;

    @NotBlank(message = "Proof of address is required")
    private String proofOfAddress;

    // Getters and Setters

    public String getCertificateOfIncorporation() {
        return certificateOfIncorporation;
    }
    public void setCertificateOfIncorporation(String certificateOfIncorporation) {
        this.certificateOfIncorporation = certificateOfIncorporation;
    }
    public String getTinDocument() {
        return tinDocument;
    }
    public void setTinDocument(String tinDocument) {
        this.tinDocument = tinDocument;
    }
    public String getUtilityBill() {
        return utilityBill;
    }
    public void setUtilityBill(String utilityBill) {
        this.utilityBill = utilityBill;
    }
    public String getAuthorizedSignatoryId() {
        return authorizedSignatoryId;
    }
    public void setAuthorizedSignatoryId(String authorizedSignatoryId) {
        this.authorizedSignatoryId = authorizedSignatoryId;
    }
    public String getProofOfAddress() {
        return proofOfAddress;
    }
    public void setProofOfAddress(String proofOfAddress) {
        this.proofOfAddress = proofOfAddress;
    }
}
