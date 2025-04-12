package com.github.diogocerqueiralima.forumservice.repositories;

import com.github.diogocerqueiralima.forumservice.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {}
