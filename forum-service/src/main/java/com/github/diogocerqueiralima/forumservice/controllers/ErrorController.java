package com.github.diogocerqueiralima.forumservice.controllers;

import com.github.diogocerqueiralima.forumservice.dto.ApiResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleValidationException(MethodArgumentNotValidException e) {

        BindingResult bindingResult = e.getBindingResult();
        String message = bindingResult.getFieldError() == null ? "" : bindingResult.getFieldError().getDefaultMessage();

        return ResponseEntity.badRequest()
                .body(new ApiResponseDto<>(message, null));
    }

}
