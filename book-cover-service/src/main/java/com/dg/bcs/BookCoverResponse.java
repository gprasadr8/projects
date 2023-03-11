package com.dg.bcs;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;

@Builder
@Data
public class BookCoverResponse {
    @Id
    private String isbn;

    private String title;

    private byte[] imageRawBytes;
}
