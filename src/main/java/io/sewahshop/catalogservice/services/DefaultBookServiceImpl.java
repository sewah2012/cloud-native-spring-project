package io.sewahshop.catalogservice.services;

import io.sewahshop.catalogservice.domains.Book;
import io.sewahshop.catalogservice.exceptions.errors.BookAlreadyExistsException;
import io.sewahshop.catalogservice.exceptions.errors.BookNotFoundException;
import io.sewahshop.catalogservice.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultBookServiceImpl implements BookService{
    private final BookRepository bookDao;

    DefaultBookServiceImpl(BookRepository bookDao) {
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
                            existingBook.publisher(),
                            newBook.title(),
                            newBook.author(),
                            newBook.price(),
                            existingBook.createdDate(),
                            existingBook.lastModifiedDate(),
                            existingBook.createdBy(),
                            existingBook.lastModifiedBy(),
                            existingBook.version()
                    );
                    return bookDao.save(bookToUpdate);
                })
                .orElseGet(() -> addBookToCatalog(newBook));
    }
}
