package com.github.diogocerqueiralima.forumservice.controllers;

import com.github.diogocerqueiralima.forumservice.dto.ApiResponseDto;
import com.github.diogocerqueiralima.forumservice.dto.CommentDto;
import com.github.diogocerqueiralima.forumservice.dto.CreateCommentDto;
import com.github.diogocerqueiralima.forumservice.dto.UpdateCommentDto;
import com.github.diogocerqueiralima.forumservice.models.Comment;
import com.github.diogocerqueiralima.forumservice.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<CommentDto>> getById(@PathVariable Long id) {

        Comment comment = commentService.getById(id);

        return ResponseEntity
                .ok(new ApiResponseDto<>("Comment retrieved successfully", comment.toDto()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<CommentDto>> update(
            @PathVariable Long id, @RequestBody @Valid UpdateCommentDto dto, @AuthenticationPrincipal Jwt jwt
    ) {

        UUID userId = UUID.fromString(jwt.getSubject());
        Comment comment = commentService.update(id, dto.content(), userId);

        return ResponseEntity
                .ok(new ApiResponseDto<>("Comment updated successfully", comment.toDto()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteById(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {

        UUID userId = UUID.fromString(jwt.getSubject());
        commentService.deleteById(id, userId);

        return ResponseEntity
                .ok(new ApiResponseDto<>("Comment deleted successfully"));
    }

}
