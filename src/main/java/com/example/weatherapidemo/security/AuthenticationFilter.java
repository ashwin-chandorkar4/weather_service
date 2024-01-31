package com.example.weatherapidemo.security;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Extract headers
        String clientId = request.getHeader("X-Client-Id");
        String clientSecret = request.getHeader("X-Client-Secret");

        // Validate headers
        if (isValidClientCredentials(clientId, clientSecret)) {
            // validation is successful, set up security context
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    clientId, clientSecret, Collections.emptyList());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            // If validation fails, send a 401 Unauthorized response
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid credentials");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean isValidClientCredentials(String clientId, String clientSecret) {
        String validClientId1 = "client1";
        String validClientSecret1 = "secret1";

        String validClientId2 = "client2";
        String validClientSecret2 = "secret2";

        // Hardcoded validation logic and values
        return (clientId.equals(validClientId1) && clientSecret.equals(validClientSecret1)) ||
                (clientId.equals(validClientId2) && clientSecret.equals(validClientSecret2));

        }
}