package com.dg.bms.rest.models;

import lombok.Data;

@Data
public class BookResponse {

    private int bookId;

    private String isbn;

    private String name;

    private String author;

    private String description;

}
