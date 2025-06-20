package com.fimepay.merchantapp.dto;

import com.fimepay.merchantapp.model.DocumentType;
import jakarta.validation.constraints.NotNull;

public class KycUploadRequest {
    @NotNull
    private DocumentType documentType;
    // file comes in via MultipartFile in controller
    public DocumentType getDocumentType() { return documentType; }
    public void setDocumentType(DocumentType documentType) { this.documentType = documentType; }
}