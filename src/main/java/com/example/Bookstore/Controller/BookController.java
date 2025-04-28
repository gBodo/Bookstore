package com.example.Bookstore.Controller;

import com.example.Bookstore.Model.Book;
import com.example.Bookstore.Service.BookService;
import com.example.Bookstore.Service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name="Book")
public class BookController {

    private final BookService bookService;
    private final ReviewService reviewService;

    @Autowired
    public BookController(BookService bookService, ReviewService reviewService) {
        this.bookService = bookService;
        this.reviewService=reviewService;
    }

    @GetMapping()
    @Operation(summary = "View all the books.")
    public ResponseEntity<List<Book>> getAllBooks(
            @RequestParam(required = false) Integer category,
            @RequestParam(required = false) String search
    ) {
        return ResponseEntity.ok(bookService.getAllBooks(category, search));
    }

    @GetMapping("/{id}")
    @Operation(summary = "View a single book's details.")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        try {
            Book book = bookService.getBookById(id);
            return ResponseEntity.ok(book);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
