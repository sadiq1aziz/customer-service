package com.app.customer.entity;

import com.app.customer.domain.Genre;
import jakarta.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator",
            sequenceName = "sequence_generator",
            allocationSize = 10)
    private Integer id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Genre favoriteGenre;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFavoriteGenre(Genre favoriteGenre) {
        this.favoriteGenre = favoriteGenre;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Genre getFavoriteGenre() {
        return favoriteGenre;
    }



}
