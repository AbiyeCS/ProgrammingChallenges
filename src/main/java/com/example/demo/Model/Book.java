package com.example.demo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@Entity
public class Book {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private int publicationYear;
    private String genre;
    @Value("${true}")
    private boolean available;
    private double price;
    private boolean returned;

}
