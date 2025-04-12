package com.github.diogocerqueiralima.forumservice.models;

import com.github.diogocerqueiralima.forumservice.dto.TopicDto;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "topics")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @CreationTimestamp
    private Instant createdAt;

    @Column(nullable = false)
    private UUID userId;

    public Topic() {}

    public Topic(String title, String content, UUID userId) {
        this.content = content;
        this.title = title;
        this.userId = userId;
    }

    public TopicDto toDto() {
        return new TopicDto(this.id, this.title, this.content, this.createdAt);
    }

}
