package com.arizal.api.handler;

import com.arizal.api.exception.AccountNotFoundException;
import com.arizal.api.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@ControllerAdvice()
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionResponseHandler.class);

    @ExceptionHandler({AccessDeniedException.class, InsufficientScopeException.class, OAuth2Exception.class})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Object> handleAccessDeniedExpetion(Exception ex) {
        logger.error(ex.getLocalizedMessage(), ex);

        HashMap<String, Object> response = new HashMap<>();
        response.put("code", HttpStatus.UNAUTHORIZED.toString());
        response.put("isError", "true");
        response.put("message", ex.getLocalizedMessage());
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Object> handleRestHttpConnectionException(Exception ex) {
        logger.error(ex.getLocalizedMessage(), ex);

        HashMap<String, Object> response = new HashMap<>();
        response.put("isError", Constants.RESPONSE.INVALID_ACCOUNT_NOT_FOUND.getCode());
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Validating all Exception
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        logger.error(ex.getLocalizedMessage(), ex);

        HashMap<String, Object> response = new HashMap<>();
        response.put("isError", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        response.put("message", "Error Core System");
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
