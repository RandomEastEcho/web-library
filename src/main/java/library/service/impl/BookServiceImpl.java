package library.service.impl;

import java.util.List;
import library.dao.BookDao;
import library.model.Book;
import library.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Book add(Book book) {
        return bookDao.add(book);
    }

    @Override
    public Book getBook(Long id) {
        return bookDao.getBook(id).orElseThrow(
                () -> new RuntimeException("Can`t find book with id: " + id));
    }

    @Override
    public void delete(Long id) {
        bookDao.delete(id);
    }

    @Override
    public Book update(Book book) {
        return bookDao.update(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }
}
