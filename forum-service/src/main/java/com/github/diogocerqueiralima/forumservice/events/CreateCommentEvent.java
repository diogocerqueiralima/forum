package com.github.diogocerqueiralima.forumservice.events;

import java.time.Instant;
import java.util.UUID;

public record CreateCommentEvent(

        Long topicId,
        String topicTitle,
        UUID topicOwnerId,
        Long commentId,
        Instant createdAt,
        String content,
        UUID userId

) {}
