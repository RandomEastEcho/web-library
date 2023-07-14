package library.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import library.dto.request.BookRequestDto;
import library.dto.response.BookResponseDto;
import library.model.Book;
import library.service.BookService;
import library.service.mapper.DtoMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final DtoMapper<Book, BookRequestDto, BookResponseDto> dtoMapper;

    public BookController(BookService bookService,
                          DtoMapper<Book, BookRequestDto, BookResponseDto> dtoMapper) {
        this.bookService = bookService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping("/actions")
    public String getBookActionsPage() {
        return "forward:/static/booksActionsPage.html";
    }

    @GetMapping("/add")
    public String getAddNewBookPage() {
        return "forward:/static/addBook.html";
    }

    @ResponseBody
    @PostMapping
    public BookResponseDto add(@RequestBody BookRequestDto requestDto) {
        Book book = bookService.add(dtoMapper.toModel(requestDto));
        return dtoMapper.toDto(book);
    }

    @GetMapping("/search-by-id")
    public String getSearchPage() {
        return "forward:/static/searchBook.html";
    }

    @GetMapping("/search-by-id-reader")
    public String getSearchPageForReader() {
        return "forward:/static/searchBookReader.html";
    }

    @ResponseBody
    @GetMapping("/{id}")
    public BookResponseDto getById(@PathVariable Long id) {
        return dtoMapper.toDto(bookService.getBook(id));
    }

    @GetMapping("/all-books")
    public String getAllBooksPage() {
        return "forward:/static/allBooks.html";
    }

    @ResponseBody
    @GetMapping
    public List<BookResponseDto> getAll() {
        return bookService.getAllBooks()
                .stream()
                .map(dtoMapper::toDto)
                .sorted(Comparator.comparing(BookResponseDto::getCategoryName))
                .collect(Collectors.toList());
    }

    @ResponseBody
    @PutMapping("/{id}")
    public BookResponseDto update(@PathVariable Long id,
                                  @RequestBody BookRequestDto requestDto) {
        Book book = dtoMapper.toModel(requestDto);
        book.setId(id);
        bookService.update(book);
        return dtoMapper.toDto(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
