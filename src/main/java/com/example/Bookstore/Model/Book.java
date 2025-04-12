package com.example.Bookstore.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name ="title",nullable = false)
    private String title;

    @Column(name= "author",nullable = false)
    private String author;

    @Column(name="price",nullable = false)
    private double price;

    @Column(name = "cover_image_url", nullable = false, columnDefinition = "TEXT")
    private String coverImageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category; // M:1 Relationship with Category

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews; // 1:N Relationship with Reviews

}
