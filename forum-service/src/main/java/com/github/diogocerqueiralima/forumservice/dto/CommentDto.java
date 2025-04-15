package com.github.diogocerqueiralima.forumservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;

public record CommentDto(

        Long id,
        Boolean edited,
        String content,
        List<Long> responses,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("topic_id") Long topicId,
        @JsonProperty("parent_id") Long parentId

) {}