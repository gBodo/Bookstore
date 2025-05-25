package com.example.Bookstore.Service;

import com.example.Bookstore.Model.Book;
import com.example.Bookstore.Model.Category;
import com.example.Bookstore.Repository.BookRepository;
import com.example.Bookstore.RequestBody.AddBookBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(Integer category, String search) {
        if (category != null && search != null) {
            return bookRepository.findByCategoryIdAndTitleContainingIgnoreCase(category, search);
        } else if (category != null) {
            return bookRepository.findByCategoryId(category);
        } else if (search != null) {
            return bookRepository.findByTitleContainingIgnoreCase(search);
        } else {
            return bookRepository.findAll();
        }
    }

    public Book getBookById(Integer id) throws RuntimeException {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    // ADMIN
    public String addBook(AddBookBody addBookBody) {
        Optional<Book> existingTitle = bookRepository.findByTitle(addBookBody.getTitle());

        if (existingTitle.isPresent()) {
            throw new IllegalArgumentException("The book is already posted.");
        }
        Book book = new Book();
        book.setAuthor(addBookBody.getAuthor());
        book.setCoverImageUrl(addBookBody.getCover_image_url());
        book.setPrice(addBookBody.getPrice());
        book.setTitle(addBookBody.getTitle());
        Category category = new Category();
        category.setId(addBookBody.getCategory_id());
        book.setCategory(category);

        bookRepository.save(book);
        return "Book added to the library.";
    }
}
