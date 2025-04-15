package com.github.diogocerqueiralima.forumservice.models;

import com.github.diogocerqueiralima.forumservice.dto.TopicDto;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
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

    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @Column(nullable = false)
    private UUID userId;

    public Topic() {}

    public Topic(String title, String content, UUID userId) {
        this(null, title, content, false, Instant.now(), userId);
    }

    public Topic(Long id, String title, String content, boolean edited, Instant createdAt, UUID userId) {
        this.comments = new ArrayList<>();
        this.id = id;
        this.title = title;
        this.content = content;
        this.edited = edited;
        this.createdAt = createdAt;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Boolean isEdited() {
        return edited;
    }
}
