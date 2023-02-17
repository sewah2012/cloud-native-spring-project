package io.sewahshop.catalogservice.services;

import io.sewahshop.catalogservice.dao.BookDao;
import io.sewahshop.catalogservice.entity.Book;
import io.sewahshop.catalogservice.exceptions.errors.BookAlreadyExistsException;
import io.sewahshop.catalogservice.exceptions.errors.BookNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DefaultBookServiceImpl implements BookService{
    private final BookDao bookDao;

    DefaultBookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }
    @Override
    public Iterable<Book> viewBookList() {
        return bookDao.findAll();
    }

    @Override
    public Book viewBookDetails(String isbn) {
        return bookDao.findByIsbn(isbn)
        .orElseThrow(() -> new BookNotFoundException(isbn));

    }
    @Override
    public Book addBookToCatalog(Book book) {
        if (bookDao.existsByIsbn(book.isbn())) {
            throw new BookAlreadyExistsException(book.isbn());
        }
        return bookDao.save(book);
    }

    @Override
    public void removeBookFromCatalog(String isbn) {
        bookDao.deleteByIsbn(isbn);
    }

    @Override
    public Book editBookDetails(String isbn, Book newBook) {
        return bookDao.findByIsbn(isbn)
                .map(existingBook -> {
                    var bookToUpdate = new Book(
                            existingBook.id(),
                            existingBook.isbn(),
                            newBook.title(),
                            newBook.author(),
                            newBook.price(),
                            existingBook.version()
                    );
                    return bookDao.save(bookToUpdate);
                })
                .orElseGet(() -> addBookToCatalog(newBook));
    }
}
