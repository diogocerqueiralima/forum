package com.github.diogocerqueiralima.forumservice.services;

import com.github.diogocerqueiralima.forumservice.exceptions.TopicNotFoundException;
import com.github.diogocerqueiralima.forumservice.exceptions.TopicOwnerException;
import com.github.diogocerqueiralima.forumservice.models.Topic;
import com.github.diogocerqueiralima.forumservice.repositories.TopicRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TopicServiceTest {

    @Mock
    private TopicRepository topicRepository;

    @InjectMocks
    private TopicService topicService;

    @Test
    public void get_topic_by_id_that_does_not_exist_should_fail() {

        when(topicRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(TopicNotFoundException.class, () -> topicService.getById(1L));
        verify(topicRepository, times(1)).findById(1L);
    }

    @Test
    public void get_topic_by_id_should_succeed() {

        Topic expected = new Topic(1L, "Title", "Content", false, Instant.now(), UUID.randomUUID());

        when(topicRepository.findById(1L)).thenReturn(Optional.of(expected));

        Topic actual = topicService.getById(1L);

        assertEquals(expected, actual);
        verify(topicRepository, times(1)).findById(1L);
    }

    @Test
    public void create_topic_should_succeed() {

        Topic expected = new Topic(1L, "Title", "Content", false, Instant.now(), UUID.randomUUID());

        when(topicRepository.save(any(Topic.class))).thenReturn(expected);

        Topic actual = topicService.create(expected.getTitle(), expected.getContent(), expected.getUserId());

        assertEquals(expected, actual);
        verify(topicRepository, times(1)).save(any(Topic.class));
    }

    @Test
    public void update_topic_that_does_not_exist_should_fail() {

        when(topicRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(TopicNotFoundException.class,
                () -> topicService.update(1, "New Title", "New Content", UUID.randomUUID())
        );

        verify(topicRepository, times(1)).findById(1L);
        verify(topicRepository, times(0)).save(any(Topic.class));
    }

    @Test
    public void update_topic_that_does_not_belong_to_user_should_fail() {

        Topic topic = new Topic(1L, "Title", "Content", false, Instant.now(), UUID.randomUUID());

        when(topicRepository.findById(1L)).thenReturn(Optional.of(topic));

        assertThrows(
                TopicOwnerException.class,
                () -> topicService.update(1, "New Title", "New Content", UUID.randomUUID())
        );

        verify(topicRepository, times(1)).findById(1L);
        verify(topicRepository, times(0)).save(any(Topic.class));
    }

}
