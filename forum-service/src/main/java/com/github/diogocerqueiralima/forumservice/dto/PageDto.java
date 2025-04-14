package com.github.diogocerqueiralima.forumservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PageDto<T>(

        List<T> content,
        @JsonProperty("current_page") Integer currentPage,
        @JsonProperty("total_pages") Integer totalPages

) {}
