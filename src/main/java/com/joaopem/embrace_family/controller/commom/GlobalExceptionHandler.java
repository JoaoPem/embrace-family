package com.joaopem.embrace_family.controller.commom;

import com.joaopem.embrace_family.dto.FieldError;
import com.joaopem.embrace_family.dto.ResponseError;
import com.joaopem.embrace_family.exceptions.DuplicateRecordException;
import com.joaopem.embrace_family.exceptions.PasswordConfirmationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> errors = e.getBindingResult().getFieldErrors().stream()
                .map(err -> new FieldError(err.getField(), err.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Validation Failed", errors);
    }

    @ExceptionHandler(DuplicateRecordException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseError handleDuplicateRecordException(DuplicateRecordException e){
        return ResponseError.conflict(e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
        String errorMessage = e.getMessage();
        return ResponseError.invalidEnumValue(errorMessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseError.illegalRole(e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseError handleAccessDeniedException(AccessDeniedException e){
        return new ResponseError(HttpStatus.FORBIDDEN.value(), e.getMessage(), List.of());
    }

    @ExceptionHandler(PasswordConfirmationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handlePasswordConfirmationException(PasswordConfirmationException e){
        return  new ResponseError(HttpStatus.BAD_REQUEST.value(),"Validation Failed", List.of(new FieldError("passwordConfirmation", e.getMessage())));
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handleIllegalStateException(IllegalStateException  e){
        return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), List.of());
    }

//    @ExceptionHandler(RuntimeException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseError handleUnhandledErrors(RuntimeException e){
//        return new ResponseError(
//                HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                "An unexpected error has occurred. Please contact management.",
//                List.of());
//    }

}
