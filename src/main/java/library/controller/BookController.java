package library.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import library.dto.request.BookRequestDto;
import library.dto.response.BookResponseDto;
import library.model.Book;
import library.service.BookService;
import library.service.mapper.DtoMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final DtoMapper<Book, BookRequestDto, BookResponseDto> dtoMapper;

    public BookController(BookService bookService,
                          DtoMapper<Book, BookRequestDto, BookResponseDto> dtoMapper) {
        this.bookService = bookService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    public BookResponseDto add(@RequestBody BookRequestDto requestDto) {
        Book book = bookService.add(dtoMapper.toModel(requestDto));
        return dtoMapper.toDto(book);
    }

    @GetMapping("/{id}")
    public BookResponseDto getById(@PathVariable Long id) {
        return dtoMapper.toDto(bookService.getBook(id));
    }

    @GetMapping
    public List<BookResponseDto> getAll() {
        return bookService.getAllBooks()
                .stream()
                .map(dtoMapper::toDto)
                .sorted(Comparator.comparing(BookResponseDto::getCategoryName))
                .collect(Collectors.toList());
    }

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
