package library.service.mapper;

import library.dto.request.CategoryRequestDto;
import library.dto.response.CategoryResponseDto;
import library.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements
        DtoMapper<Category, CategoryRequestDto, CategoryResponseDto> {
    @Override
    public CategoryResponseDto toDto(Category model) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(model.getId());
        categoryResponseDto.setName(model.getName());
        return categoryResponseDto;
    }

    @Override
    public Category toModel(CategoryRequestDto requestDto) {
        Category category = new Category();
        category.setName(requestDto.getName());
        return category;
    }
}
