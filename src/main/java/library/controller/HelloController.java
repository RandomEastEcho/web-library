package library.controller;

import library.dto.request.CategoryRequestDto;
import library.dto.response.CategoryResponseDto;
import library.model.Category;
import library.service.CategoryService;
import library.service.mapper.DtoMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class HelloController {
/*
    private final CategoryService categoryService;
    private final DtoMapper<Category, CategoryRequestDto, CategoryResponseDto>
            dtoMapper;

    public HelloController(CategoryService categoryService,
                              DtoMapper<Category, CategoryRequestDto,
                                      CategoryResponseDto> dtoMapper) {
        this.categoryService = categoryService;
        this.dtoMapper = dtoMapper;
    }

    //Category Update
    @GetMapping("categories/update/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {
        Category category = categoryService.get(id);
        model.addAttribute("category", category);
        return "/WEB-INFO/views/categoryUpdate.jsp";
    }



    //Category Delete
    @GetMapping("/categories/delete")
    public ModelAndView showDeletionForm() {
        return new ModelAndView("/WEB-INFO/views/categoryDeletionById.jsp", "category", new Category());
    }

    @PostMapping("categories/delete-result")
    public String deleteById(@RequestParam("id") Long id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }

// Get by id (+ controller)
    @GetMapping("/categories/search")
    public ModelAndView showCategoryForm() {
        return new ModelAndView("/WEB-INFO/views/categoryByIdSearch.jsp", "category", new Category());
    }
    @PostMapping("/categories/search-result")
    public String getById(@RequestParam("id") Long id) {
        return "redirect:/categories/" + id;
    }


//Add category

    @GetMapping("/new")
    public ModelAndView newCategoryForm(Map<String, Object> model) {
        CategoryRequestDto requestDto = new CategoryRequestDto();
        model.put("category", requestDto);
        return new ModelAndView("/WEB-INFO/views/new_category.jsp", model);
    }

    @PostMapping(value = "/save")
    public String saveCategory(@ModelAttribute("category") CategoryRequestDto requestDto) {
        categoryService.add(dtoMapper.toModel(requestDto));
        return "redirect:/categories";
    }


    @GetMapping
    public ModelAndView getAll() {
        Map<String, Object> model = new HashMap<>();
        List<CategoryResponseDto> categoriesDto = categoryService.getAll()
                .stream()
                .map(dtoMapper::toDto)
                .collect(Collectors.toList());
        model.put("category", categoriesDto);
        return new ModelAndView("/WEB-INFO/views/allCategories.jsp", model);
    }*/
}
