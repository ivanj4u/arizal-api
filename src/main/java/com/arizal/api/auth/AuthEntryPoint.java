package com.arizal.api.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpRequest, HttpServletResponse httpResponse, AuthenticationException ex)
            throws IOException, ServletException {
        /**
         * Custom Unauthorized Handler (Using Entry Point)
         */
        try {
            HashMap<String, Object> response = new HashMap<>();
            response.put("code", HttpStatus.UNAUTHORIZED.toString());
            response.put("isError", "true");
            response.put("message", ex.getLocalizedMessage());

            // Write Response
            PrintWriter out = httpResponse.getWriter();
            ObjectMapper objectMapper= new ObjectMapper();
            String json = objectMapper.writeValueAsString(response);

            httpResponse.setStatus(HttpStatus.OK.value());
            httpResponse.setContentType("application/json");
            httpResponse.setCharacterEncoding("UTF-8");

            out.print(json);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
