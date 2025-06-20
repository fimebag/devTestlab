package com.fimepay.merchantapp.dto;

import com.fimepay.merchantapp.model.TeamRole;

// 4.1 InviteUserRequest
public class InviteUserRequest {
    private String email;
    private String name;
    private TeamRole role;
    // getters/setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public TeamRole getRole() { return role; }
    public void setRole(TeamRole role) { this.role = role; }
}