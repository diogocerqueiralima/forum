package com.github.diogocerqueiralima.forumservice.models;

import com.github.diogocerqueiralima.forumservice.dto.CommentDto;
import com.github.diogocerqueiralima.forumservice.events.CreateCommentEvent;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private UUID userId;

    private Boolean edited;

    @CreationTimestamp
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Comment> responses;

    public Comment() {}

    public Comment(Long id, String content, UUID userId, Boolean edited, Topic topic, Comment parent) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.edited = edited;
        this.topic = topic;
        this.parent = parent;
        this.responses = new ArrayList<>();
    }

    public Comment(String content, UUID userId, boolean edited, Topic topic, Comment parent) {
        this(null, content, userId, edited, topic, parent);
    }

    public Comment(String content, UUID userId, Topic topic, Comment parent) {
        this(content, userId, false, topic, parent);
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public UUID getUserId() {
        return userId;
    }

    public Topic getTopic() {
        return topic;
    }

    public Comment getParent() {
        return parent;
    }

    public CommentDto toDto() {
        return new CommentDto(
                this.id,
                this.edited,
                this.content,
                this.responses.stream().map(comment -> comment.id).toList(),
                this.createdAt,
                this.topic.getId(),
                this.parent == null ? null : this.parent.id
        );
    }

    public CreateCommentEvent toCreateCommentEvent() {
        return new CreateCommentEvent(
                this.topic.getId(), this.topic.getTitle(), this.topic.getUserId(),
                this.id, this.createdAt, this.content, this.userId
        );
    }

}
