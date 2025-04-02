package com.project.BlogApplication.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public Map<String, String> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException ex){
        log.warn(ex.getMessage());
        return Map.of("message","Category Already Exists");
    }

    @ExceptionHandler(CategoryHasPostsException.class)
    public Map<String, String> handleCategoryHasPostsException(CategoryHasPostsException ex){
        log.warn(ex.getMessage());
        return Map.of("message","Category Has Posts");
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public Map<String, String> handleCategoryNotFoundException(CategoryNotFoundException ex){
        log.warn(ex.getMessage());
        return Map.of("message","Category Not Found");
    }

}
