package com.github.diogocerqueiralima.forumservice.controllers;

import com.github.diogocerqueiralima.forumservice.dto.ApiResponseDto;
import com.github.diogocerqueiralima.forumservice.dto.CreateTopicDto;
import com.github.diogocerqueiralima.forumservice.dto.TopicDto;
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

    @GetMapping
    public ResponseEntity<List<Topic>> getAll() {
        return ResponseEntity.ok(this.topicService.getAll());
    }

}
