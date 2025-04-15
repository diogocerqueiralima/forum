package com.github.diogocerqueiralima.forumservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCommentDto(

        @NotBlank String content,
        @JsonProperty("parent_id") Long parentId,
        @NotNull @JsonProperty("topic_id") Long topicId

) {}
