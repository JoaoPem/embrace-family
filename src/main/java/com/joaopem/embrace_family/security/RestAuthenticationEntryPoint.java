package com.joaopem.embrace_family.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        Map<String, Object> bodyResponse = new HashMap<>();
        bodyResponse.put("error", "Unauthorized");
        bodyResponse.put("message", authException.getMessage());
        bodyResponse.put("path", request.getServletPath());
        bodyResponse.put("timestamp", Instant.now().toString());

        new ObjectMapper().writeValue(response.getOutputStream(), bodyResponse);

    }
}
