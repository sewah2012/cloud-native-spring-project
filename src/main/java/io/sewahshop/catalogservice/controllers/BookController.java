package io.sewahshop.catalogservice.controllers;

import io.sewahshop.catalogservice.entity.Book;
import io.sewahshop.catalogservice.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    BookController(BookService bookService) {this.bookService = bookService;}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book post(@Valid @RequestBody Book book) {
        return bookService.addBookToCatalog(book);
    }
    @PutMapping("/{isbn}")
    public Book put(@PathVariable String isbn, @Valid @RequestBody Book book) {
        return bookService.editBookDetails(isbn, book);
    }

    @GetMapping("{isbn}")
    public Book viewBookDetails(@PathVariable String isbn) {
        return bookService.viewBookDetails(isbn);
    }
}
