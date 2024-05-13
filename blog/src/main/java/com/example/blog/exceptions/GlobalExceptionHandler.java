package com.example.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
   /* @ExceptionHandler(ResourceNotFoundException.class)
    public <ApiResponse> ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException resourceNotFoundException){
        String message = resourceNotFoundException.getMessage();
        ApiResponse apiResponse =new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
    }*/

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
//        Map<String,String> resp=new HashMap<>();
//        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error)->{
//                String fieldName = ((FieldError) error).getField();
//                String message =error.getDefaultMessage();
//                resp.put(fieldName,message);
//
//    });
//        return  new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
//    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String>handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String,String>errorMap=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        return errorMap;
    }
    }