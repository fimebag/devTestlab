package com.fimepay.merchantapp.dto;

import jakarta.validation.constraints.*;
import java.util.List;

public class CreateApiKeyRequest {

    @NotBlank
    private String name;

    @NotEmpty
    private List<@NotBlank String> scopes;

    @NotNull
    @Min(1)
    private Integer rateLimit;

    // Getters and Setters

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<String> getScopes() {
        return scopes;
    }
    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }
    public Integer getRateLimit() {
        return rateLimit;
    }
    public void setRateLimit(Integer rateLimit) {
        this.rateLimit = rateLimit;
    }
}
