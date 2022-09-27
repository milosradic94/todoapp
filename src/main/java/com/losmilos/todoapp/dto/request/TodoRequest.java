package com.losmilos.todoapp.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter @Setter
public class TodoRequest {

    @NotEmpty
    @Size(max = 200)
    private String name;

    @NotEmpty @Size(max = 200)
    private String description;

    @NotNull
    private LocalDateTime datetime;

    @NotNull
    private Boolean finished;
}
