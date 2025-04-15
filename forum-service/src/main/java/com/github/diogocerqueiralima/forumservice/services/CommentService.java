package com.github.diogocerqueiralima.forumservice.services;

import com.github.diogocerqueiralima.forumservice.exceptions.CommentNotFoundException;
import com.github.diogocerqueiralima.forumservice.models.Comment;
import com.github.diogocerqueiralima.forumservice.models.Topic;
import com.github.diogocerqueiralima.forumservice.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final TopicService topicService;

    public CommentService(CommentRepository commentRepository, TopicService topicService) {
        this.commentRepository = commentRepository;
        this.topicService = topicService;
    }

    public Comment getById(long id) {
        return commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
    }

    public Comment create(String content, UUID userId, Long topicId, Long parentId) {

        Comment parent = parentId == null ? null : getById(parentId);
        Topic topic = topicService.getById(topicId);

        return commentRepository.save(new Comment(content, userId, topic, parent));
    }

}
