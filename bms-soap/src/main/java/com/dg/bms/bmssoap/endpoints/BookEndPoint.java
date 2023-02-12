package com.dg.bms.bmssoap.endpoints;

import com.dg.bms.bmssoap.BookService;
import com.dg_bms.springsoap.gen.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class BookEndPoint {

    private static final String NAMESPACE_URI = "http://www.dg-bms.com/springsoap/gen";
    private final BookService bookService;

    public BookEndPoint(BookService bookService) {
        this.bookService = bookService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookByIdRequest")
    @ResponsePayload
    public GetBookByIdResponse getBookById(@RequestPayload GetBookByIdRequest getBookByIdRequest){
        return  bookService.getBookById(getBookByIdRequest.getBookId());
    }



    @PayloadRoot(namespace = NAMESPACE_URI,localPart = "getAllBooks")
    @ResponsePayload
    public GetAllBooksResponse getAllBooks(@RequestPayload GetAllBooks request){
        return bookService.getAllBooks();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addBookRequest")
    @ResponsePayload
    public AddBookResponse addNewBook(@RequestPayload AddBookRequest addBookRequest){
        return  bookService.addNewBook(addBookRequest);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateBookRequest")
    @ResponsePayload
    public UpdateBookResponse updateBook(@RequestPayload UpdateBookRequest updateBookRequest){
        return bookService.updateBook(updateBookRequest);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteBookRequest")
    @ResponsePayload
    public DeleteBookResponse deleteBook(@RequestPayload DeleteBookRequest deleteBookRequest){
        return  bookService.deleteBook(deleteBookRequest);
    }
}
