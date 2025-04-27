package com.github.diogocerqueiralima.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserDto(

        String email,
        @JsonProperty("given_name") String givenName,
        @JsonProperty("family_name") String familyName,
        @JsonProperty("full_name") String fullName,
        String picture

) {}
