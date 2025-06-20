package com.fimepay.merchantapp.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.*;
import java.util.Collections;
import java.io.IOException;

/**
 * Extracts the JWT from the Authorization header,
 * validates it, and populates the SecurityContext.
 */



@Component
public class JwtAuthenticationFilter implements Filter {
    
    private final JwtUtil jwtUtil;
    
    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String authHeader = httpRequest.getHeader("Authorization");
        
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            
            try {
                if (jwtUtil.validateToken(token)) {
                    String username = jwtUtil.extractUsername(token);
                    String userId = jwtUtil.extractUserId(token);
                    
                    if (username != null) {
                        // Create custom principal with both username and userId
                        JwtUserPrincipal principal = new JwtUserPrincipal(username, userId);
                        
                        UsernamePasswordAuthenticationToken auth = 
                            new UsernamePasswordAuthenticationToken(principal, null, Collections.emptyList());
                        
                        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    }
                } else {
                    // Invalid token - clear any existing authentication
                    SecurityContextHolder.clearContext();
                    sendErrorResponse(httpResponse, "Invalid or expired token");
                    return;
                }
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
                sendErrorResponse(httpResponse, "Token validation failed");
                return;
            }
        }
        
        chain.doFilter(request, response);
    }
    
    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"" + message + "\"}");
    }
}