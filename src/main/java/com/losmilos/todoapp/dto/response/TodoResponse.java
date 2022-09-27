package com.losmilos.todoapp.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class TodoResponse {

    private String name;

    private String description;

    private LocalDateTime datetime;

    private Boolean finished;
}
