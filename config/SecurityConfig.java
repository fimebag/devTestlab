package com.fimepay.merchantapp.config;

import com.fimepay.merchantapp.idempotency.filter.IdempotencyFilter;
import com.fimepay.merchantapp.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;
    
    @Value("${app.cors.allowed-origins:http://localhost:3000}")
    private List<String> allowedOrigins;
    
    @Value("${app.security.enable-cors:true}")
    private boolean corsEnabled;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           IdempotencyFilter idempotencyFilter) throws Exception {
        http
            // ─── Basic Security Configuration ─────────────────────────────────
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sm -> sm
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            
            // ─── CORS Configuration ───────────────────────────────────────────
            .cors(cors -> {
                if (corsEnabled) {
                    cors.configurationSource(corsConfigurationSource());
                } else {
                    cors.disable();
                }
            })
            
            // ─── Request Authorization ────────────────────────────────────────
            .authorizeHttpRequests(auth -> auth
                // Public endpoints - no authentication required
                .requestMatchers(
                    // Health check endpoints
                    "/health/**",
                    "/actuator/health",
                    "/actuator/info",
                    
                    // API Documentation
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**",
                    "/swagger-resources/**",
                    "/webjars/**",
                    
                    // Public API endpoints (if any)
                    "/api/public/**",
                    
                    // Error handling
                    "/error"
                ).permitAll()
                
                // Protected endpoints - require authentication
                .requestMatchers(
                    "/api/merchant/**",
                    "/api/payout/**",
                    "/api/transaction/**"
                ).authenticated()
                
                // All other requests require authentication
                .anyRequest().authenticated()
            )
            
            // ─── Exception Handling ───────────────────────────────────────────
            .exceptionHandling(exceptions -> exceptions
                // Custom authentication entry point (removes HTTP Basic popup)
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json");
                    String errorResponse = String.format(
                        "{\"error\": \"Unauthorized\", \"message\": \"Valid JWT token required\", \"timestamp\": \"%s\", \"path\": \"%s\"}",
                        java.time.Instant.now().toString(),
                        request.getRequestURI()
                    );
                    response.getWriter().write(errorResponse);
                })
                
                // Handle access denied (403)
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.setContentType("application/json");
                    String errorResponse = String.format(
                        "{\"error\": \"Access Denied\", \"message\": \"Insufficient privileges\", \"timestamp\": \"%s\", \"path\": \"%s\"}",
                        java.time.Instant.now().toString(),
                        request.getRequestURI()
                    );
                    response.getWriter().write(errorResponse);
                })
            );

        // ─── Filter Chain Configuration ───────────────────────────────────────
        http
            // 1) JWT Authentication - validates token and sets security context
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            
            // 2) Idempotency - prevents duplicate operations (after authentication)
            .addFilterAfter(idempotencyFilter, JwtAuthenticationFilter.class);

        return http.build();
    }

    // ─── CORS Configuration ───────────────────────────────────────────────────
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Allowed origins (configure via application.yml)
        configuration.setAllowedOrigins(allowedOrigins);
        
        // Allowed methods
        configuration.setAllowedMethods(Arrays.asList(
            "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"
        ));
        
        // Allowed headers
        configuration.setAllowedHeaders(Arrays.asList(
            "Authorization",
            "Content-Type",
            "X-Requested-With",
            "Accept",
            "Origin",
            "Access-Control-Request-Method",
            "Access-Control-Request-Headers",
            // Idempotency header
            "Idempotency-Key"
        ));
        
        // Expose headers that client can access
        configuration.setExposedHeaders(Arrays.asList(
            "Access-Control-Allow-Origin",
            "Access-Control-Allow-Credentials",
            "X-Request-ID"
        ));
        
        // Allow credentials (cookies, authorization headers)
        configuration.setAllowCredentials(true);
        
        // Cache preflight requests for 1 hour
        configuration.setMaxAge(3600L);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // ─── Password Encoder ─────────────────────────────────────────────────────
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12); // Increased strength for better security
    }
}
