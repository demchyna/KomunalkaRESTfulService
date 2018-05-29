package com.mdem.komunalka.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdem.komunalka.model.common.ErrorInfo;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    private Logger logger;

    @Autowired
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        String errorURL = request.getRequestURL().toString();
        String errorMessage = exception.getMessage();
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.FORBIDDEN.value(), errorURL, errorMessage);

        PrintWriter out = response.getWriter();
        String jsonString = new ObjectMapper().writeValueAsString(errorInfo);

        out.print(jsonString);
        out.flush();

        logger.error(exception.getMessage(), exception);
    }
}