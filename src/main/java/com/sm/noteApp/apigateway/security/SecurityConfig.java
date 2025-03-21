package com.sm.noteApp.apigateway.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/*
 * SecurityConfig for security configuration
 *
 * @author Shilpi
 * @since 2025-03-06
 */

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        LOGGER.debug("Configuring security web filter chain...");
        http
                .csrf(csrf -> {
                    LOGGER.debug("Disabling CSRF for Gateway...");
                    csrf.disable();
                }) // Disable CSRF for Gateway
                .authorizeExchange(exchanges -> {
                    LOGGER.debug("Configuring authorization for exchanges...");
                    exchanges
                            .anyExchange().permitAll(); // Allow all requests
                    LOGGER.debug("Allowed all requests...");
                });

        LOGGER.debug("Building security web filter chain...");
        return http.build();
    }
}