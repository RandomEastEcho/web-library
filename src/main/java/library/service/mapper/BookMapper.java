package library.service.mapper;

import library.dto.request.BookRequestDto;
import library.dto.response.BookResponseDto;
import library.model.Book;
import library.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements DtoMapper<Book, BookRequestDto, BookResponseDto> {
    private final CategoryService categoryService;

    public BookMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public BookResponseDto toDto(Book model) {
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(model.getId());
        bookResponseDto.setName(model.getName());
        bookResponseDto.setDescription(model.getDescription());
        bookResponseDto.setAuthorName(model.getAuthorName());
        bookResponseDto.setCategoryName(model.getCategory().getName());
        return bookResponseDto;
    }

    @Override
    public Book toModel(BookRequestDto requestDto) {
        Book book = new Book();
        book.setName(requestDto.getName());
        book.setDescription(requestDto.getDescription());
        book.setAuthorName(requestDto.getAuthorName());
        book.setCategory(categoryService.get(requestDto.getCategoryId()));
        return book;
    }
}
