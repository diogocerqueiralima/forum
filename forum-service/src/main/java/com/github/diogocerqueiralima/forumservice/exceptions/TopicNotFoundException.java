package com.github.diogocerqueiralima.forumservice.exceptions;

public class TopicNotFoundException extends RuntimeException {

    public TopicNotFoundException(long id) {
        super("Topic with id " + id + " not found");
    }

}
