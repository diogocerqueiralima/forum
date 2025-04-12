package com.github.diogocerqueiralima.forumservice.services;

import com.github.diogocerqueiralima.forumservice.models.Topic;
import com.github.diogocerqueiralima.forumservice.repositories.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Topic create(String title, String content, UUID userId) {
        return topicRepository.save(
                new Topic(title, content, userId)
        );
    }

    public List<Topic> getAll() {
        return this.topicRepository.findAll();
    }

}
