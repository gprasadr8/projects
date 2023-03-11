package com.dg.bms.bmssoap;

import com.dg.bms.bmssoap.entity.DBBook;
import com.dg.bms.bmssoap.repository.BookRepository;
import com.dg_bms.springsoap.gen.*;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public GetBookByIdResponse getBookById(int bookId){
        Optional<DBBook> optionalDBBook = bookRepository.findById(bookId);
        GetBookByIdResponse response = new GetBookByIdResponse();
        ServiceStatus status = new ServiceStatus();
        if(optionalDBBook.isPresent()){
            response.setBook(convertFromDBBook(optionalDBBook.get()));
        }else {
            status.setStatusCode(StatusCode.NOT_FOUND);
            status.setMessage("Book is not found for the given bookId:"+bookId);
        }
        response.setServiceStatus(status);
        return response;
    }
    private Book convertFromDBBook(DBBook dbBook) {
        Book book = new Book();
        book.setId(dbBook.getId());
        book.setIsbn(dbBook.getIsbn());
        book.setName(dbBook.getName());
        book.setAuthor(dbBook.getAuthor());
        book.setDescription(dbBook.getDesc());
        return book;
    }

    public GetAllBooksResponse getAllBooks() {
        GetAllBooksResponse response = new GetAllBooksResponse();
        response.getBooks().addAll(bookRepository.findAll().stream().map(this::convertFromDBBook).collect(Collectors.toList()));
        return response;
    }

    public AddBookResponse addNewBook(AddBookRequest addBookRequest) {
       DBBook dbBook =  bookRepository.save(convertToDBBook(addBookRequest.getBook()));
       AddBookResponse response = new AddBookResponse();
       response.setBook(convertFromDBBook(dbBook));
        return response;
    }

    private DBBook convertToDBBook(Book inputBook) {
        DBBook dbBook = new DBBook();
        dbBook.setIsbn(inputBook.getIsbn());
        dbBook.setName(inputBook.getName());
        dbBook.setDesc(inputBook.getDescription());
        dbBook.setAuthor(inputBook.getAuthor());
        return dbBook;
    }

    public UpdateBookResponse updateBook(UpdateBookRequest updateBookRequest) {
        Book inputBook = updateBookRequest.getBook();
        UpdateBookResponse response = new UpdateBookResponse();
        ServiceStatus status = new ServiceStatus();
        if(bookRepository.existsById(inputBook.getId())){
            DBBook dbBook = convertToDBBook(inputBook);
            dbBook.setId(inputBook.getId());
            dbBook = bookRepository.save(dbBook);
           status.setStatusCode(StatusCode.SUCCESS);
           status.setMessage("Book is updated successfully.");
           response.setBook(convertFromDBBook(dbBook));
           response.setServiceStatus(status);
        }else{
            status.setStatusCode(StatusCode.NOT_FOUND);
            status.setMessage("No Book found with id: "+inputBook.getId());
            response.setServiceStatus(status);
        }

        return response;
    }

    public DeleteBookResponse deleteBook(DeleteBookRequest deleteBookRequest) {
        DeleteBookResponse response = new DeleteBookResponse();
        ServiceStatus status = new ServiceStatus();
        int bookId = deleteBookRequest.getBookId();
        if(bookRepository.existsById(bookId)){
               bookRepository.deleteById(bookId);
               status.setStatusCode(StatusCode.SUCCESS);
               status.setMessage("Book is deleted successfully");
               response.setServiceStatus(status);
        }else{
            status.setStatusCode(StatusCode.NOT_FOUND);
            status.setMessage("Book is not found for the given id: "+bookId);
            response.setServiceStatus(status);
        }
        return response;
    }
}
