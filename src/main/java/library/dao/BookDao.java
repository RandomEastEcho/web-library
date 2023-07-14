package library.dao;

import java.util.List;
import java.util.Optional;
import library.model.Book;

public interface BookDao {
    Book add(Book book);

    Optional<Book> getBook(Long id);

    List<Book> getAllBooks();

    void delete(Long id);

    Book update(Book book);
}
