package io.sewahshop.catalogservice.repositories;

import io.sewahshop.catalogservice.Config.PersistenceConfig;
import io.sewahshop.catalogservice.domains.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJdbcTest
@Import(PersistenceConfig.class)
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
@ActiveProfiles("integration")
class BookRepositoryJdbcTest {
    @Autowired
    private BookRepository  bookRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;


    @Test
    void findBookByIsbnWhenExisting() {
        var bookIsbn = "1234561237";
        var book = Book.of(bookIsbn, "Emmanuel Sahr Sewah", "Title", "Author", 12.90);
        jdbcAggregateTemplate.insert(book);
        Optional<Book> actualBook = bookRepository.findByIsbn(bookIsbn);
        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().isbn()).isEqualTo(book.isbn());

    }

    @Test
    void whenCreateBookNotAuthenticatedThenNoAuditMetadata() {
        var bookIsbn = "1234561237";
        var bookToCreate = Book.of(bookIsbn, "Emmanuel Sahr Sewah", "Title", "Author", 12.90);
        var createdBook = bookRepository.save(bookToCreate);

        assertThat(createdBook.createdBy()).isNull();
        assertThat(createdBook.lastModifiedBy()).isNull();
    }

    @Test
    @WithMockUser("john")
    void whenCreateBookAuthenticatedThenAuditMetadata() {
        var bookIsbn = "1234561237";
        var bookToCreate = Book.of(bookIsbn, "Emmanuel Sahr Sewah", "Title", "Author", 12.90);
        var createdBook = bookRepository.save(bookToCreate);

        assertThat(createdBook.createdBy())
                .isEqualTo("john");
        assertThat(createdBook.lastModifiedBy())
                .isEqualTo("john");
    }

}
