package com.example.Bookstore.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cart_items")
public class Cart_item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart; // FK → cart.id

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book; // FK → books.id

    @Column(name = "quantity", nullable = false)
    private Integer quantity;


}
