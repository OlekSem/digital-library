package com.example.digitallibrary.service;

import com.example.digitallibrary.entity.Book;
import com.example.digitallibrary.exception.FileStorageException;
import com.example.digitallibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Resource> downloadBook(Long id) {

        try {

            Book book = bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
            Resource resource = new UrlResource(Paths.get(book.getFilePath()).toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new FileStorageException("Could not read file: " + book.getFilePath());
            }
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (IOException e) {
            throw new FileStorageException("Failed to download file ", e);
        }
    }
}
