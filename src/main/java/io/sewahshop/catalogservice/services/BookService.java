package io.sewahshop.catalogservice.services;

import io.sewahshop.catalogservice.entity.Book;

import java.util.Optional;

public interface BookService {
    Iterable<Book> viewBookList();
    Book viewBookDetails(String isbn);
    Book addBookToCatalog(Book book);
    void removeBookFromCatalog(String isbn);
    Book editBookDetails(String isbn, Book newBook);
}
