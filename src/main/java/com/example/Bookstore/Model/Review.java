package com.example.Bookstore.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating", nullable = false)
    private Integer rating; // Rating between 1 and 5

    @Column(name = "comment", columnDefinition = "TEXT", nullable = true)
    private String comment; // Optional comment

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // FK → users.id

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book; // FK → books.id
}
