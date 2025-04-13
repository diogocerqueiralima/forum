package com.github.diogocerqueiralima.forumservice.controllers;

import com.github.diogocerqueiralima.forumservice.dto.ApiResponseDto;
import com.github.diogocerqueiralima.forumservice.exceptions.TopicNotFoundException;
import com.github.diogocerqueiralima.forumservice.exceptions.TopicOwnerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleValidationException(MethodArgumentNotValidException e) {

        BindingResult bindingResult = e.getBindingResult();
        String message = bindingResult.getFieldError() == null ? "" : bindingResult.getFieldError().getDefaultMessage();

        return ResponseEntity.badRequest()
                .body(new ApiResponseDto<>(message));
    }

    @ExceptionHandler(TopicNotFoundException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleBadRequest(Exception e) {
        return ResponseEntity.badRequest()
                .body(new ApiResponseDto<>(e.getMessage()));
    }

    @ExceptionHandler(TopicOwnerException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleForbidden(Exception e) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ApiResponseDto<>(e.getMessage()));
    }

}
