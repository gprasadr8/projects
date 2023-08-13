package com.dg.bms.rest;

import com.dg.bms.rest.entity.BookEntity;
import com.dg.bms.rest.models.BookRequest;
import com.dg.bms.rest.models.BookResponse;
import com.dg.bms.rest.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public BookResponse getBookById(int bookId){
       return convertFromEntity(bookRepository.findById(bookId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Book not found with bookId:"+bookId)));
    }


    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream().map(this::convertFromEntity).collect(Collectors.toList());
    }

    public BookResponse addNewBook(BookRequest newBook) {
       return convertFromEntity(bookRepository.save(convertToEntity(newBook)));
    }



    public BookResponse updateBook(BookRequest updateBookRequest) {

        if(bookRepository.existsById(updateBookRequest.getBookId())){
            BookEntity bookEntity = convertToEntity(updateBookRequest);
            bookEntity.setId(updateBookRequest.getBookId());
             return convertFromEntity(bookRepository.save(bookEntity));

        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No Book found with id: "+updateBookRequest.getBookId());
        }
    }

    public String deleteBook(int bookId) {

        if(bookRepository.existsById(bookId)){
            bookRepository.deleteById(bookId);
            return "Book with Id: "+bookId+ "is successfully deleted.";
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Book is not found for the given id: "+bookId );
        }
    }

    private BookEntity convertToEntity(BookRequest inputBook) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setIsbn(inputBook.getIsbn());
        bookEntity.setName(inputBook.getName());
        bookEntity.setDesc(inputBook.getDescription());
        bookEntity.setAuthor(inputBook.getAuthor());
        return bookEntity;
    }

    private BookResponse convertFromEntity(BookEntity bookEntity) {
        BookResponse book = new BookResponse();
        book.setBookId(bookEntity.getId());
        book.setIsbn(bookEntity.getIsbn());
        book.setName(bookEntity.getName());
        book.setAuthor(bookEntity.getAuthor());
        book.setDescription(bookEntity.getDesc());
        return book;
    }
}
