package com.github.diogocerqueiralima.forumservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record TopicDto(
        Long id,
        String title,
        String content,
        Boolean edited,
        @JsonProperty("created_at") Instant createdAt
) {}
