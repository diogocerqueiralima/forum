package com.github.diogocerqueiralima.forumservice.controllers;

import com.github.diogocerqueiralima.forumservice.dto.ApiResponseDto;
import com.github.diogocerqueiralima.forumservice.dto.CommentDto;
import com.github.diogocerqueiralima.forumservice.dto.CreateCommentDto;
import com.github.diogocerqueiralima.forumservice.models.Comment;
import com.github.diogocerqueiralima.forumservice.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<CommentDto>> create(@RequestBody @Valid CreateCommentDto dto, @AuthenticationPrincipal Jwt jwt) {

        UUID userId = UUID.fromString(jwt.getSubject());
        Comment comment = commentService.create(dto.content(), userId, dto.topicId(), dto.parentId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponseDto<>("Comment created successfully", comment.toDto()));
    }

}
