package com.project.BlogApplication.Exception;

public class CategoryHasPostsException extends RuntimeException {
    public CategoryHasPostsException(String message) {
        super(message);
    }
}
