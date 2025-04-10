package com.task.springapplication.services;

import com.task.springapplication.ResourceException;
import com.task.springapplication.dto.NewsDto;
import com.task.springapplication.entities.Category;
import com.task.springapplication.entities.News;
import com.task.springapplication.repositories.CategoryRepository;
import com.task.springapplication.repositories.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class NewsCRUDService implements CRUDService<NewsDto> {

    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @ExceptionHandler
    public NewsDto getById(Integer id) {
        System.out.println("Get by ID: " + id);
        if (!newsRepository.existsById(id)) {
            HashMap<String, String> errorMessage = new HashMap<>();
            errorMessage.put("message", "Новость с ID " + id + " не найдена.");
            throw new ResourceException(HttpStatus.NOT_FOUND, errorMessage);
        }
        return mapToDto(newsRepository.findById(id).orElseThrow());
    }

    @Override
    public Collection<NewsDto> getAll() {
        System.out.println("Get all");
        return newsRepository.findAll()
                .stream()
                .map(NewsCRUDService::mapToDto)
                .toList();
    }

    @Override
    public void create(NewsDto item) {
        System.out.println("Create new item: " + item);
        News news = mapToEntity(item);
        String categoryTitle = item.getCategory();
        ArrayList<Category> list = new ArrayList<>(categoryRepository.findAll());
        if (list.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1 && !categoryTitle.equals(list.get(i).getTitle())) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (categoryTitle.equals(list.get(i).getTitle())) {
                Category category = categoryRepository.findById(list.get(i).getId()).orElseThrow();
                news.setCategory(category);
                newsRepository.save(news);
                break;
            }
        }
    }

    @Override
    public void update(Integer id, NewsDto item) {
        System.out.println("Update id: " + id);
        if (!newsRepository.existsById(id)) {
            return;
        }
        News news = mapToEntity(item);
        String categoryTitle = item.getCategory();
        ArrayList<Category> list = new ArrayList<>(categoryRepository.findAll());
        if (list.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        for (int i = 0; i < list.size(); i++) {
            if (!categoryTitle.equals(list.get(i).getTitle())) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (categoryTitle.equals(list.get(i).getTitle())) {
                Category category = categoryRepository.findById(list.get(i).getId()).orElseThrow();
                news.setCategory(category);
                newsRepository.save(news);
                break;
            }
        }
    }

    @Override
    public void delete(Integer id) {
        System.out.println("Delete id: " + id);
        if (!newsRepository.existsById(id)) {
            HashMap<String, String> errorMessage = new HashMap<>();
            errorMessage.put("message", "Новость с ID " + id + " не найдена.");
            throw new ResourceException(HttpStatus.NOT_FOUND, errorMessage);
        }
        newsRepository.deleteById(id);
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    public static NewsDto mapToDto(News news) {
        NewsDto newsDto = new NewsDto();
        newsDto.setId(news.getId());
        newsDto.setTitle(news.getTitle());
        newsDto.setText(news.getText());
        newsDto.setDate(news.getTime());
        newsDto.setCategory(news.getCategory().getTitle());
        return newsDto;
    }

    public static News mapToEntity(NewsDto newsDto) {
        News news = new News();
        news.setId(newsDto.getId());
        news.setTitle(newsDto.getTitle());
        news.setText(newsDto.getText());
        news.setTime(newsDto.getDate());
        return news;
    }
}
