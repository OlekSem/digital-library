package com.example.digitallibrary.controller;

import com.example.digitallibrary.repository.BookRepository;
import com.example.digitallibrary.service.BookService;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;


    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/book/{id}/download.epub")
    public ResponseEntity<Resource> downloadEpub(@PathVariable Long id) {
        return bookService.downloadBook(id);
    }
}
