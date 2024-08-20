package com.krishnaintech.blog.exceptions;

import com.krishnaintech.blog.payload.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception){
        ApiResponseDto responseDto = new ApiResponseDto();
        responseDto.setMessage(exception.getMessage());
        responseDto.setSuccess(false);
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }
}
