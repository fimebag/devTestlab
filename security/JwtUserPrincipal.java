package com.fimepay.merchantapp.security;

import java.security.Principal;

public class JwtUserPrincipal implements Principal {
    private final String username;
    private final String userId;
    
    public JwtUserPrincipal(String username, String userId) {
        this.username = username;
        this.userId = userId;
    }
    
    @Override
    public String getName() {
        return username;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getUserId() {
        return userId;
    }
}