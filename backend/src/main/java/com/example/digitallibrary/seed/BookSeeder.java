package com.example.digitallibrary.seed;

import com.example.digitallibrary.entity.Book;
import com.example.digitallibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.digitallibrary.util.EpubUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

@Component
public class BookSeeder implements CommandLineRunner {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EpubUtil epubUtil;

    @Override
    public void run(String... args) throws Exception {
        if (bookRepository.count() == 0) {
            seedBooks();
        }
    }
    private void seedBooks() {
        Book book1 = new Book();
        book1.setIsbn("978-0141439518");
        book1.setTitle("Pride and Prejudice");
        book1.setAuthor("Jane Austen");
        try {
            book1.setPages(epubUtil.estimatePages("books/978-0141439518.epub"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            book1.setPages(1);
        }
        book1.setFilePath("books/978-0141439518.epub");
        book1.setPublishedDate(LocalDate.of(1813, 1, 28));

        Book book3 = new Book();
        book3.setIsbn("978-0743273565");
        book3.setTitle("The Great Gatsby");
        book3.setAuthor("F. Scott Fitzgerald");
        try {
            book3.setPages(epubUtil.estimatePages("books/978-0743273565.epub"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            book3.setPages(1);
        }
        book3.setFilePath("books/978-0743273565.epub");
        book3.setPublishedDate(LocalDate.of(1925, 4, 10));

        bookRepository.saveAll(Arrays.asList(book1, book3));
        System.out.println("Successfully seeded " + bookRepository.count() + " books.");
    }
}
