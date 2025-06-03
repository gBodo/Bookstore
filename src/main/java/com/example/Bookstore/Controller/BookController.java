package com.example.Bookstore.Controller;

import com.example.Bookstore.Model.Book;
import com.example.Bookstore.Service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name="Book")
public class BookController {

    private final BookService bookService;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    @Operation(summary = "View all the books.")
    public ResponseEntity<List<Book>> getAllBooks(
            @RequestParam(required = false) Integer category,
            @RequestParam(required = false) String search,
            Pageable pageable
            ) {
        logger.info("Showed all the books.");
        return ResponseEntity.ok(bookService.getAllBooks(category, search, pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "View a single book's details.")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        try {
            Book book = bookService.getBookById(id);
            logger.info("Showed a book's details.");
            return ResponseEntity.ok(book);
        } catch (Exception e) {
            logger.info("Failed to show a book's details!");
            return ResponseEntity.notFound().build();
        }
    }
}
