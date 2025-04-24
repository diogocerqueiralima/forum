package com.github.diogocerqueiralima.forumservice.exceptions;

public class CommentOwnerException extends RuntimeException {

    public CommentOwnerException() {
        super("You are not the owner of this comment");
    }

}
