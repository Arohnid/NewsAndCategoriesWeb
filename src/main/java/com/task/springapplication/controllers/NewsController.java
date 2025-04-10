package com.task.springapplication.controllers;

import com.task.springapplication.dto.NewsDto;
import com.task.springapplication.services.NewsCRUDService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    private final NewsCRUDService newsService;

    public NewsController(NewsCRUDService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/{id}")
    public NewsDto getNewById(@PathVariable Integer id) {
        return newsService.getById(id);
    }

    @GetMapping
    public Collection<NewsDto> getAll() {
        return newsService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNews(@RequestBody NewsDto newsDto) {
        newsService.create(newsDto);
    }

    @PutMapping("/{id}")
    public void updateNews(@PathVariable Integer id, @RequestBody NewsDto newsDto) {
        newsService.update(id, newsDto);
    }

    @DeleteMapping("/{id}")
    public void deleteNews(@PathVariable Integer id) {
        newsService.delete(id);
    }
}
