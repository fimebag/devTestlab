package com.fimepay.merchantapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.security")
public class SecurityProperties {
    
    private boolean enableCors = true;
    private Cors cors = new Cors();
    private Idempotency idempotency = new Idempotency();
    
    // Getters and setters
    public boolean isEnableCors() { return enableCors; }
    public void setEnableCors(boolean enableCors) { this.enableCors = enableCors; }
    public Cors getCors() { return cors; }
    public void setCors(Cors cors) { this.cors = cors; }
    public Idempotency getIdempotency() { return idempotency; }
    public void setIdempotency(Idempotency idempotency) { this.idempotency = idempotency; }
    
    public static class Cors {
        private String[] allowedOrigins = {"http://localhost:3000"};
        private long maxAge = 3600;
        
        public String[] getAllowedOrigins() { return allowedOrigins; }
        public void setAllowedOrigins(String[] allowedOrigins) { this.allowedOrigins = allowedOrigins; }
        public long getMaxAge() { return maxAge; }
        public void setMaxAge(long maxAge) { this.maxAge = maxAge; }
    }
    
    public static class Idempotency {
        private String[] protectedPaths = {"/api/payout/**", "/api/transaction/**"};
        private long ttlMinutes = 60;
        
        public String[] getProtectedPaths() { return protectedPaths; }
        public void setProtectedPaths(String[] protectedPaths) { this.protectedPaths = protectedPaths; }
        public long getTtlMinutes() { return ttlMinutes; }
        public void setTtlMinutes(long ttlMinutes) { this.ttlMinutes = ttlMinutes; }
    }
}