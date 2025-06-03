package com.example.Bookstore.Service;

import com.example.Bookstore.Model.Book;
import com.example.Bookstore.Model.Category;
import com.example.Bookstore.Repository.BookRepository;
import com.example.Bookstore.RequestBody.AddBookBody;
import com.example.Bookstore.RequestBody.DeleteBookBody;
import com.example.Bookstore.RequestBody.UpdatePriceBookBody;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(Integer category, String search, Pageable pageable) {
        if (category != null && search != null) {
            return bookRepository.findByCategoryIdAndTitleContainingIgnoreCase(category, search, pageable);
        } else if (category != null) {
            return bookRepository.findByCategoryId(category, pageable);
        } else if (search != null) {
            return bookRepository.findByTitleContainingIgnoreCase(search, pageable);
        } else {
            return bookRepository.findAll((Sort) pageable);
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

    @Transactional
    public String deleteBook(DeleteBookBody deleteBookBody){
        Optional<Book> existingTitle = bookRepository.findByTitle(deleteBookBody.getTitle());
        if (existingTitle.isEmpty()) {
            throw new IllegalArgumentException("There is no book to be deleted.");
        }
        bookRepository.deleteBookByTitle(deleteBookBody.getTitle());
        return "Book deleted.";
    }

    public String updateBookPrice(UpdatePriceBookBody updatePriceBookBody){
        Book booktitle = bookRepository.findByTitle(updatePriceBookBody.getTitle()).get();

        if (booktitle!=null) {
            booktitle.setPrice(updatePriceBookBody.getPrice());
            bookRepository.save(booktitle);
        }
        else{
            throw new IllegalArgumentException("There is no book to be updated.");
        }
        return "Book price updated.";
    }
}
