package library.service;

import java.util.List;
import library.model.Book;

public interface BookService {
    Book add(Book book);

    Book getBook(Long id);

    List<Book> getAllBooks();

    void delete(Long id);

    Book update(Book book);
}
