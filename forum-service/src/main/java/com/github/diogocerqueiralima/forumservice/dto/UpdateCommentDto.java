package com.github.diogocerqueiralima.forumservice.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateCommentDto(

        @NotBlank String content

) {}
