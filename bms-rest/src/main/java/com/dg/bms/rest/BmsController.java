package com.dg.bms.rest;

import com.dg.bms.rest.models.BookRequest;
import com.dg.bms.rest.models.BookResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BmsController {

    private final BookService bookService;

    public BmsController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookResponse> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{bookId}")
    public BookResponse getBookById(@PathVariable int bookId){
        return bookService.getBookById(bookId);
    }

    @PostMapping
    public BookResponse addNewBook(@RequestBody BookRequest bookRequest){
        return bookService.addNewBook(bookRequest);
    }

    @PutMapping
    public BookResponse updateBook(@RequestBody BookRequest bookRequest){
        return bookService.updateBook(bookRequest);
    }

    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable int bookId){
        return bookService.deleteBook(bookId);
    }
}
