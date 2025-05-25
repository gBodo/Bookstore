package com.example.Bookstore.Repository;

import com.example.Bookstore.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByCategoryId(Integer categoryId);
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByCategoryIdAndTitleContainingIgnoreCase(Integer categoryId, String title);

    Optional<Book> findById(Integer id);
    Optional<Book> findByTitle(String title);

}