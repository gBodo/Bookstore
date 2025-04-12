package com.example.Bookstore.Repository;

import com.example.Bookstore.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByCategoryId(Long categoryId);
    List<Book> findByTitleContainingIgnoreCase(String title);
}