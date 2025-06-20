

package com.fimepay.merchantapp.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fimepay.merchantapp.model.DocumentType;
import com.fimepay.merchantapp.model.KycStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KycDocumentDTO {
    private UUID id;
    private DocumentType documentType;
    private String filePath;
    private KycStatus status;
    private String rejectionReason;
    private LocalDateTime uploadedAt;
    private LocalDateTime reviewedAt;
    // getters/setters…
    public UUID getId() {
        return id;
    }
    public DocumentType getDocumentType() {
        return documentType;
    }
    public String getFilePath() {
        return filePath;
    }
    public KycStatus getStatus() {
        return status;
    }
    public String getRejectionReason() {
        return rejectionReason;
    }
    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }
    public LocalDateTime getReviewedAt() {
        return reviewedAt;
    }
}