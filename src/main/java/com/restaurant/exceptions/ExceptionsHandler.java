package com.restaurant.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleInternal(
            Exception ex, WebRequest req
    ) {
        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                req.getDescription(false)
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ExceptionResponse> handleAppException(
            AppException ex, WebRequest req
    ) {

        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                req.getDescription(false)
        );

        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(Exception ex, WebRequest req){
        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                req.getDescription(false)
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NotNullException.class)
    public ResponseEntity<ExceptionResponse> handleNotNullException(Exception ex, WebRequest req){
        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                req.getDescription(false)
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidJwtAuthenticationException(Exception ex, WebRequest req){
        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                req.getDescription(false)
        );

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidRoleException(Exception ex, WebRequest req){
        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                req.getDescription(false)
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleAccessDeniedException(Exception ex, WebRequest req){
        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                req.getDescription(false)
        );

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }


}
