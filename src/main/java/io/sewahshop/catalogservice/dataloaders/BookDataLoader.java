package io.sewahshop.catalogservice.dataloaders;

import io.sewahshop.catalogservice.domains.Book;
import io.sewahshop.catalogservice.repositories.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testdata")
public class BookDataLoader {
    //    private final BookDao bookDao;
//
//    BookDataLoader(BookDao bookDao) {
//        this.bookDao = bookDao;
//    }
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void loadBookTestData() {
//        var book1 = Book.of("1234567891", "Northern Lights",
//                "Lyra Silverstar", 9.90);
//        var book2 = Book.of("1234567892", "Polar Journey",
//                "Iorek Polarson", 12.90);
//        bookDao.save(book1);
//        bookDao.save(book2);
//    }
    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        bookRepository.deleteAll();
        var book1 = Book.of("1234567891", "Emmanuel Sewah","Northern Lights",
                "Lyra Silverstar", 9.90);
        var book2 = Book.of("1234567892", "Hawa Sewah", "Polar Journey",
                "Iorek Polarson", 12.90);
        bookRepository.saveAll(List.of(book1, book2));
    }
}

