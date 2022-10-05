package com.losmilos.todoapp.dto.mapper;

import com.losmilos.todoapp.dto.request.TodoRequest;
import com.losmilos.todoapp.dto.response.TodoResponse;
import com.losmilos.todoapp.entity.Todo;
import com.losmilos.todoapp.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface TodoMapper {

    Todo toEntity(TodoRequest todo);

    TodoResponse toResponse(Todo todo, User user);

}
