package com.task.springapplication.services;

import com.task.springapplication.ResourceException;
import com.task.springapplication.dto.CategoryDto;
import com.task.springapplication.entities.Category;
import com.task.springapplication.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class CategoryCRUDService implements CRUDService<CategoryDto> {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto getById(Integer id) {
        System.out.println("Get by id: " + id);
        if(!categoryRepository.existsById(id)){
            HashMap<String, String> errorMessage = new HashMap<>();
            errorMessage.put("message", "Категория с ID " + id + " не найдена.");
            throw new ResourceException(HttpStatus.NOT_FOUND, errorMessage);
        }
        return mapToDto(categoryRepository.findById(id).orElseThrow());
    }

    @Override
    public Collection<CategoryDto> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryCRUDService::mapToDto)
                .toList();
    }

    @Override
    public void create(CategoryDto item) {
        System.out.println("Create new item: " + item);
        categoryRepository.save(mapToEntity(item));
    }

    @Override
    public void update(Integer id, CategoryDto item) {
        categoryRepository.save(mapToEntity(item));
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }

    public static CategoryDto mapToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setNews(category.getNews()
                .stream()
                .map(NewsCRUDService::mapToDto)
                .toList());
        return categoryDto;
    }

    public static Category mapToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setTitle(categoryDto.getTitle());
        category.setNews(categoryDto.getNews()
                .stream()
                .map(NewsCRUDService::mapToEntity)
                .toList());
        return category;
    }
}
