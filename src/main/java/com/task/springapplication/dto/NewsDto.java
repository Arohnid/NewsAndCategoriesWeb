package com.task.springapplication.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.Instant;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsDto {
    private int id;
    private String title;
    private String text;
    private Instant date;
    private Integer categoryId;
    private String category;
}
