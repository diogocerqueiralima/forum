package com.github.diogocerqueiralima.forumservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record TopicDto(
        long id,
        String title,
        String content,
        @JsonProperty("created_at") Instant createdAt
) {}
