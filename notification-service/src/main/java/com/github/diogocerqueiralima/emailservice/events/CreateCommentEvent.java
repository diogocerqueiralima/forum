package com.github.diogocerqueiralima.emailservice.events;

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
