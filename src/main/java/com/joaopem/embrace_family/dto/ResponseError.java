package com.joaopem.embrace_family.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ResponseError(int status, String message, List<FieldError> erros) {

    public static ResponseError defaultResponse(String errorMessage){
        return new ResponseError(HttpStatus.BAD_REQUEST.value(), errorMessage, List.of());
    }

    public static ResponseError conflict(String errorMessage){
        return new ResponseError(HttpStatus.CONFLICT.value(), errorMessage, List.of());
    }

    public static ResponseError invalidEnumValue(String errorMessage) {
        return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Invalid enum value", List.of(new FieldError("enum", errorMessage)));
    }

    public static ResponseError illegalRole(String errorMessage){
        return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Validation Failed", List.of(new FieldError("role", errorMessage)));
    }
}
