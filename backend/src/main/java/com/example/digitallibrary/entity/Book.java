package com.example.digitallibrary.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String isbn;

    @Column(nullable = false)
    private String title;

    private String author;

    @Column(name = "total_pages")
    private Integer pages;

    private String filePath;

    private LocalDate publishedDate;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
