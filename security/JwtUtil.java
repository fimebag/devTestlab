package com.fimepay.merchantapp.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    
    private final String secret;
    private final String expectedIssuer;
    private final String expectedAudience;

    public JwtUtil(
        @Value("${jwt.secret}") String secret,
        @Value("${jwt.issuer}") String expectedIssuer,
        @Value("${jwt.audience}") String expectedAudience
    ) {
        this.secret = secret;
        this.expectedIssuer = expectedIssuer;
        this.expectedAudience = expectedAudience;
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(io.jsonwebtoken.io.Decoders.BASE64.decode(secret.trim()));
    }

    // ─── VALIDATION METHODS (Primary Purpose) ────────────────────────────────────

    /**
     * Validates token issued by customerapp with full security checks
     * @param token JWT token to validate
     * @return true if token is valid, false otherwise
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .requireIssuer(expectedIssuer)      // Must be from customerapp
                .requireAudience(expectedAudience)   // Must be for merchantapp
                .build()
                .parseClaimsJws(token);
            
            logger.debug("Token validation successful");
            return true;
            
        } catch (ExpiredJwtException e) {
            logger.warn("Token expired: {}", e.getMessage());
            return false;
        } catch (UnsupportedJwtException e) {
            logger.warn("Unsupported token format: {}", e.getMessage());
            return false;
        } catch (MalformedJwtException e) {
            logger.warn("Malformed token: {}", e.getMessage());
            return false;
        } catch (SecurityException | SignatureException e) {
            logger.warn("Invalid token signature: {}", e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            logger.warn("Empty or null token: {}", e.getMessage());
            return false;
        } catch (JwtException e) {
            logger.error("JWT validation error: {}", e.getMessage());
            return false;
        }
    }

    // ─── DATA EXTRACTION METHODS ─────────────────────────────────────────────────

    /**
     * Extracts username from validated token
     * @param token JWT token (should be pre-validated)
     * @return username or null if extraction fails
     */
    public String extractUsername(String token) {
        try {
            Claims claims = extractClaims(token);
            return claims.getSubject();
        } catch (JwtException e) {
            logger.warn("Failed to extract username: {}", e.getMessage());
            return null;
        }
    }

    /**
     * Extracts user ID from validated token
     * @param token JWT token (should be pre-validated)
     * @return userId or null if extraction fails
     */
    public String extractUserId(String token) {
        try {
            Claims claims = extractClaims(token);
            return claims.get("userId", String.class);
        } catch (JwtException e) {
            logger.warn("Failed to extract userId: {}", e.getMessage());
            return null;
        }
    }

    /**
     * Extracts custom claims from validated token
     * @param token JWT token (should be pre-validated)
     * @param claimName name of the claim to extract
     * @param claimType expected type of the claim
     * @return claim value or null if extraction fails
     */
    public <T> T extractClaim(String token, String claimName, Class<T> claimType) {
        try {
            Claims claims = extractClaims(token);
            return claims.get(claimName, claimType);
        } catch (JwtException e) {
            logger.warn("Failed to extract claim '{}': {}", claimName, e.getMessage());
            return null;
        }
    }

    /**
     * Gets token expiration time
     * @param token JWT token (should be pre-validated)
     * @return expiration date or null if extraction fails
     */
    public Date extractExpiration(String token) {
        try {
            Claims claims = extractClaims(token);
            return claims.getExpiration();
        } catch (JwtException e) {
            logger.warn("Failed to extract expiration: {}", e.getMessage());
            return null;
        }
    }

    // ─── INTERNAL HELPER ─────────────────────────────────────────────────────────

    /**
     * Internal method to extract claims with full validation
     * This method performs the same validation as validateToken()
     */
    private Claims extractClaims(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .requireIssuer(expectedIssuer)
                .requireAudience(expectedAudience)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ─── UTILITY METHODS ─────────────────────────────────────────────────────────

    /**
     * Quick check if token format is valid (basic structure check)
     * Does not validate signature or claims
     */
    public boolean hasValidFormat(String token) {
        if (token == null || token.trim().isEmpty()) {
            return false;
        }
        
        // JWT should have 3 parts separated by dots
        String[] parts = token.split("\\.");
        return parts.length == 3;
    }

    /**
     * Extract token from Authorization header
     * @param authHeader Authorization header value
     * @return token without "Bearer " prefix, or null if invalid format
     */
    public String extractTokenFromHeader(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}