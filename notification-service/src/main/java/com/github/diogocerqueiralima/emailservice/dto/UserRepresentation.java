package com.github.diogocerqueiralima.emailservice.dto;

public record UserRepresentation(

        String id,
        String username,
        String firstName,
        String lastName,
        String email

) {}
