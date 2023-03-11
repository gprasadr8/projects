package com.dg.bcs.service;

import com.dg.bcs.BookCoverResponse;
import com.dg.bcs.persistence.model.BookCoverEntity;
import com.dg.bcs.persistence.repo.BookCoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookCoverService {

    @Autowired
    private BookCoverRepository bookCoverRepository;

    @Autowired
    private ImageService imageService;

    public BookCoverResponse getBookCover(String isbn){
        BookCoverEntity bookCoverEntity = bookCoverRepository.findById(isbn).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"BookCover is not found."));
        return convertBookCoverResponse(bookCoverEntity);
    }

    private BookCoverResponse convertBookCoverResponse(BookCoverEntity bookCoverEntity) {
        return BookCoverResponse.builder()
                .title(bookCoverEntity.getTitle())
                .isbn(bookCoverEntity.getIsbn())
                .imageRawBytes(imageService.readImage(bookCoverEntity.getImageLocation()))
                .build();
    }

    public List<BookCoverResponse> getBookCovers() {
        return bookCoverRepository.findAll().stream().map(this::convertBookCoverResponse).collect(Collectors.toList());
    }
}
