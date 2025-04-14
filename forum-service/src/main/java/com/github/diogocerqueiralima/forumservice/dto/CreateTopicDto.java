package com.github.diogocerqueiralima.forumservice.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateTopicDto(

        @NotBlank(message = "Title is required") String title,
        @NotBlank(message = "Content is required") String content

) {}
