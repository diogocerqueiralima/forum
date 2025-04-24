package com.github.diogocerqueiralima.forumservice.controllers;

import com.github.diogocerqueiralima.forumservice.dto.*;
import com.github.diogocerqueiralima.forumservice.models.Topic;
import com.github.diogocerqueiralima.forumservice.services.TopicService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<TopicDto>> getById(@PathVariable long id) {

        Topic topic = topicService.getById(id);

        return ResponseEntity
                .ok(new ApiResponseDto<>("Topic retrieved successfully", topic.toDto()));
    }

    @GetMapping("/page/{number}")
    public ResponseEntity<ApiResponseDto<PageDto<TopicDto>>> getPage(@PathVariable int number) {

        Page<Topic> page = topicService.getPage(number);

        return ResponseEntity
                .ok(
                        new ApiResponseDto<>(
                                "Topics retrieved successfully",
                                new PageDto<>(page.stream().map(Topic::toDto).toList(), number, page.getTotalPages())
                        )
                );
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<TopicDto>> create(
            @RequestBody @Valid CreateTopicDto dto, @AuthenticationPrincipal Jwt jwt
    ) {

        UUID userId = UUID.fromString(jwt.getSubject());
        Topic topic = topicService.create(dto.title(), dto.content(), userId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponseDto<>("Topic created successfully", topic.toDto()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<TopicDto>> update(
            @PathVariable long id, @RequestBody @Valid UpdateTopicDto dto, @AuthenticationPrincipal Jwt jwt
    ) {

        UUID userId = UUID.fromString(jwt.getSubject());
        Topic topic = topicService.update(id, dto.title(), dto.content(), userId);

        return ResponseEntity
                .ok(new ApiResponseDto<>("Topic updated successfully", topic.toDto()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteById(@PathVariable long id, @AuthenticationPrincipal Jwt jwt) {

        UUID userId = UUID.fromString(jwt.getSubject());
        topicService.deleteById(id, userId);

        return ResponseEntity
                .ok(new ApiResponseDto<>("Topic deleted successfully"));
    }

}
