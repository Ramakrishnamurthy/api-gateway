package com.sm.noteApp.ApiGateway.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

/*@Component*/
/*@Order(-1)*/ // Ensures execution order
public class JwtFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        System.out.println("Intercepting request: " + exchange.getRequest().getURI());

        // Example: Add a custom header
        exchange.getResponse().getHeaders().add("X-Custom-Header", "Reactive");
        
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");


        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        	 exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        	//exchange.getResponse().getWriter().write("Missing or invalid token");
        	  return exchange.getResponse().setComplete();
        }
        String token = authHeader.substring(7);  // Extract the token (removes "Bearer ")

        // If token validation fails, respond with Unauthorized
        if (!JwtUtil.validateToken(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }
    
    
    

    
}