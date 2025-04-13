package com.github.diogocerqueiralima.forumservice.models;

import com.github.diogocerqueiralima.forumservice.dto.TopicDto;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
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

    @Column(nullable = false)
    private Boolean edited;

    @CreationTimestamp
    private Instant createdAt;

    @Column(nullable = false)
    private UUID userId;

    public Topic() {}

    public Topic(String title, String content, UUID userId) {
        this(null, title, content, false, userId);
    }

    public Topic(Long id, String title, String content, boolean edited, UUID userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.edited = edited;
        this.userId = userId;
    }

    public TopicDto toDto() {
        return new TopicDto(this.id, this.title, this.content, this.edited, this.createdAt);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public UUID getUserId() {
        return userId;
    }

}
