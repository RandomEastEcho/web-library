package library.controller;

import java.util.List;
import java.util.stream.Collectors;
import library.dto.request.CategoryRequestDto;
import library.dto.response.CategoryResponseDto;
import library.model.Category;
import library.service.CategoryService;
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
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final DtoMapper<Category, CategoryRequestDto, CategoryResponseDto>
            dtoMapper;

    public CategoryController(CategoryService categoryService,
                              DtoMapper<Category, CategoryRequestDto,
                                          CategoryResponseDto> dtoMapper) {
        this.categoryService = categoryService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    public CategoryResponseDto add(@RequestBody CategoryRequestDto requestDto) {
        return dtoMapper.toDto(categoryService.add(dtoMapper.toModel(requestDto)));
    }

    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return dtoMapper.toDto(categoryService.get(id));
    }

    @GetMapping
    public List<CategoryResponseDto> getAll() {
        return categoryService.getAll()
                .stream()
                .map(dtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto requestDto) {
        Category category = dtoMapper.toModel(requestDto);
        category.setId(id);
        categoryService.update(category);
        return dtoMapper.toDto(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
