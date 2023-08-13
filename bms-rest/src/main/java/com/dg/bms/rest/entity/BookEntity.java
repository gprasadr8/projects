package com.dg.bms.rest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "books")
@Setter
@Getter
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String isbn;

    private String name;

    private String author;

    private String desc;
}
