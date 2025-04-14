package com.github.diogocerqueiralima.forumservice.services;

import com.github.diogocerqueiralima.forumservice.exceptions.InvalidPageException;
import com.github.diogocerqueiralima.forumservice.exceptions.TopicNotFoundException;
import com.github.diogocerqueiralima.forumservice.exceptions.TopicOwnerException;
import com.github.diogocerqueiralima.forumservice.models.Topic;
import com.github.diogocerqueiralima.forumservice.repositories.TopicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TopicService {

    public final static int PAGE_SIZE = 10;

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Topic getById(long id) {
        return topicRepository.findById(id).orElseThrow(() -> new TopicNotFoundException(id));
    }

    public Topic update(long id, String title, String content, UUID userId) {

        Topic topic = getById(id);

        if (!topic.getUserId().equals(userId))
            throw new TopicOwnerException();

        title = title == null ? topic.getTitle() : title;
        content = content == null ? topic.getContent() : content;

        return topicRepository.save(
                new Topic(topic.getId(), title, content, true, topic.getUserId())
        );
    }

    public Topic create(String title, String content, UUID userId) {
        return topicRepository.save(
                new Topic(title, content, userId)
        );
    }

    public void deleteById(long id, UUID userId) {

        Topic topic = getById(id);

        if (!topic.getUserId().equals(userId))
            throw new TopicOwnerException();

        topicRepository.delete(topic);
    }

    public Page<Topic> getPage(int number) {

        if (number < 1)
            throw new InvalidPageException();

        PageRequest pageRequest = PageRequest.of(number - 1, PAGE_SIZE);

        return topicRepository.findAll(pageRequest);
    }

}
