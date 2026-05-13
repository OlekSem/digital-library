package com.example.digitallibrary.repository;

import com.example.digitallibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatusCode;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> getBookById(Long id);
}
