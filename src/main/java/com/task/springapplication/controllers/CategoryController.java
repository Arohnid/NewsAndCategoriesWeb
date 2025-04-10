package com.task.springapplication.controllers;


import com.task.springapplication.dto.CategoryDto;
import com.task.springapplication.services.CategoryCRUDService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "/api/categories")
public class CategoryController {
    private final CategoryCRUDService categoryService;

    public CategoryController(CategoryCRUDService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(path = "/{id}")
    public CategoryDto getById(@PathVariable Integer id) {
        return categoryService.getById(id);
    }

    @GetMapping
    public Collection<CategoryDto> getAll() {
        return categoryService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CategoryDto categoryDto) {
        categoryService.create(categoryDto);
    }

    @PutMapping(path = "/{id}")
    public void update(@PathVariable Integer id, @RequestBody CategoryDto categoryDto) {
        categoryDto.setId(id);
        categoryService.update(id, categoryDto);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Integer id) {
        categoryService.delete(id);
    }

}
