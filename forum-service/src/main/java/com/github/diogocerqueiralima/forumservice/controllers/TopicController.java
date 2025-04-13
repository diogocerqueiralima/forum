package com.github.diogocerqueiralima.forumservice.controllers;

import com.github.diogocerqueiralima.forumservice.dto.ApiResponseDto;
import com.github.diogocerqueiralima.forumservice.dto.CreateTopicDto;
import com.github.diogocerqueiralima.forumservice.dto.TopicDto;
import com.github.diogocerqueiralima.forumservice.dto.UpdateTopicDto;
import com.github.diogocerqueiralima.forumservice.models.Topic;
import com.github.diogocerqueiralima.forumservice.services.TopicService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<TopicDto>>> getAll() {

        List<Topic> topics = topicService.getAll();

        return ResponseEntity
                .ok(
                        new ApiResponseDto<>(
                                "Topics retrieved successfully",
                                topics.stream().map(Topic::toDto).toList()
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
