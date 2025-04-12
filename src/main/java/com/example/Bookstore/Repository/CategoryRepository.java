package com.example.Bookstore.Repository;

import com.example.Bookstore.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}