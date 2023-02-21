package io.sewahshop.catalogservice.dao;

import io.sewahshop.catalogservice.domains.Book;

import java.util.Optional;

public interface BookDao {
    Iterable<Book> findAll();
    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
    Book save(Book book);
    void deleteByIsbn(String isbn);
}
