package com.losmilos.todoapp.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoDto {
    private String name;
    private String description;
    private LocalDateTime datetime;
    private Boolean finished;
}
