package com.dg.bcs.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "BOOK_COVER")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCoverEntity {

    @Id
    private String isbn;

    private String title;

    private String imageLocation;

}
