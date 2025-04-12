package com.github.diogocerqueiralima.forumservice.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "topics")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Long userId;

    public Topic() {}

    public Topic(String title, long userId) {
        this.id = 0L;
        this.createdAt = new Date();
        this.title = title;
        this.userId = userId;
    }

}
