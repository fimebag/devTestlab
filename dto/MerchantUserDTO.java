package com.fimepay.merchantapp.dto;

import com.fimepay.merchantapp.model.TeamRole;

import java.util.UUID;

public class MerchantUserDTO {
    private UUID id;
    private String email;
    private String name;
    private TeamRole role;
    private boolean active;
    // getters/setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public TeamRole getRole() { return role; }
    public void setRole(TeamRole role) { this.role = role; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
