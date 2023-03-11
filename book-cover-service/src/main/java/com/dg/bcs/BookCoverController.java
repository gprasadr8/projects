package com.dg.bcs;

import com.dg.bcs.service.BookCoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("book-covers")
public class BookCoverController {

    @Autowired
    private BookCoverService bookCoverService;

    @GetMapping
    public List<BookCoverResponse> getBookCovers(){
        return bookCoverService.getBookCovers();
    }
    @GetMapping("/{isbn}")
    public BookCoverResponse getBookCover(@PathVariable("isbn") String isbn){
        return bookCoverService.getBookCover(isbn);
    }
}
