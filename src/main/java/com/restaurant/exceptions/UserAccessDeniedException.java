package com.restaurant.exceptions;

import org.springframework.security.access.AccessDeniedException;

public class UserAccessDeniedException extends AccessDeniedException {

    public UserAccessDeniedException(String message) {
        super(message);
    }
}
