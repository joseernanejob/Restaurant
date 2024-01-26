package com.restaurant.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class ExceptionResponse {
    private Date timeStamp;
    private String message;
    private String details;


}
