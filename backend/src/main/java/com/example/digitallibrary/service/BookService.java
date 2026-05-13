package com.example.digitallibrary.service;

import com.example.digitallibrary.entity.Book;
import com.example.digitallibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> getBookById(Long id) {
        Optional<Book> book = bookRepository.getBookById(id);
        if(book.isPresent()) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}