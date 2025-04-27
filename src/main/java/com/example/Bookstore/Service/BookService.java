package com.example.Bookstore.Service;

import com.example.Bookstore.Constants.UserRole;
import com.example.Bookstore.Model.Book;
import com.example.Bookstore.Model.Category;
import com.example.Bookstore.Model.User;
import com.example.Bookstore.Repository.BookRepository;
import com.example.Bookstore.RequestBody.AddBookBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public String addBook(AddBookBody addBookBody) {
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
