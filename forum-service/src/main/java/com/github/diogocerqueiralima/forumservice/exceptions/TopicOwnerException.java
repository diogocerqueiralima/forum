package com.github.diogocerqueiralima.forumservice.exceptions;

public class TopicOwnerException extends RuntimeException {

    public TopicOwnerException() {
        super("You are not the owner of this topic");
    }

}
