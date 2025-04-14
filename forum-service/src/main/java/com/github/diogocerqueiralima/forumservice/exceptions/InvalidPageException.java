package com.github.diogocerqueiralima.forumservice.exceptions;

public class InvalidPageException extends RuntimeException {

    public InvalidPageException() {
        super("Invalid page number");
    }

}
