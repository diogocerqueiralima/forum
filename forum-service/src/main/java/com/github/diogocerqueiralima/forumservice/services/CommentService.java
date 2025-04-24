package com.github.diogocerqueiralima.forumservice.services;

import com.github.diogocerqueiralima.forumservice.exceptions.CommentNotFoundException;
import com.github.diogocerqueiralima.forumservice.exceptions.CommentOwnerException;
import com.github.diogocerqueiralima.forumservice.models.Comment;
import com.github.diogocerqueiralima.forumservice.models.Topic;
import com.github.diogocerqueiralima.forumservice.producers.NotificationProducer;
import com.github.diogocerqueiralima.forumservice.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final TopicService topicService;
    private final NotificationProducer notificationProducer;

    public CommentService(CommentRepository commentRepository, TopicService topicService, NotificationProducer notificationProducer) {
        this.commentRepository = commentRepository;
        this.topicService = topicService;
        this.notificationProducer = notificationProducer;
    }

    public Comment getById(long id) {
        return commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
    }

    public Comment create(String content, UUID userId, Long topicId, Long parentId) {

        Comment parent = parentId == null ? null : getById(parentId);
        Topic topic = topicService.getById(topicId);
        Comment comment = commentRepository.save(new Comment(content, userId, topic, parent));

        notificationProducer.send(comment);

        return comment;
    }

    public Comment update(long id, String content, UUID userId) {

        Comment comment = getById(id);

        if (!comment.getUserId().equals(userId))
            throw new CommentOwnerException();

        return commentRepository.save(
                new Comment(
                        comment.getId(), content, comment.getUserId(),
                        true, comment.getTopic(), comment.getParent()
                )
        );
    }

    public void deleteById(long id, UUID userId) {

        Comment comment = getById(id);

        if (!comment.getUserId().equals(userId))
            throw new CommentOwnerException();

        commentRepository.delete(comment);
    }

}
