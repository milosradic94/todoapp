package com.losmilos.todoapp.controller;

import com.losmilos.todoapp.dto.mapper.TodoMapperImpl;
import com.losmilos.todoapp.dto.request.TodoRequest;
import com.losmilos.todoapp.dto.response.TodoResponse;
import com.losmilos.todoapp.entity.Todo;
import com.losmilos.todoapp.services.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class TodoController {

    private final TodoService todoService;

    private final TodoMapperImpl todoMapper;

    @GetMapping("/todo/{userId}")
    public ResponseEntity<Page<TodoResponse>> getPaged(@PathVariable Long userId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<Todo> todoPaged = todoService.getPaged(userId, page, size);
        Page<TodoResponse> todoResponse = todoPaged.map(todo -> todoMapper.toResponse(todo, todo.getUser()));
        return new ResponseEntity<Page<TodoResponse>>(todoResponse, HttpStatus.OK);
    }

    @PostMapping("/todo/{userId}/create")
    public ResponseEntity<TodoResponse> create(@PathVariable Long userId, @Valid @RequestBody TodoRequest todoRequest) {
        Todo todo = todoService.create(userId, todoMapper.toEntity(todoRequest));
        TodoResponse todoResponse = todoMapper.toResponse(todo, todo.getUser());
        return new ResponseEntity<TodoResponse>(todoResponse, HttpStatus.CREATED);
    }

    @PostMapping("/todo/{userId}/finished/{id}")
    public ResponseEntity<TodoResponse> markAsFinished(@PathVariable Long userId, @PathVariable Long id) {
        Todo todo = todoService.markAsFinished(userId, id);
        TodoResponse todoResponse = todoMapper.toResponse(todo, todo.getUser());
        return new ResponseEntity<TodoResponse>(todoResponse, HttpStatus.OK);
    }

    @PutMapping("/todo/{userId}/update/{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable Long userId, @PathVariable Long id, @Valid @RequestBody TodoRequest todoRequest) {
        Todo todo = todoService.update(userId, id, todoMapper.toEntity(todoRequest));
        TodoResponse todoResponse = todoMapper.toResponse(todo, todo.getUser());
        return new ResponseEntity<TodoResponse>(todoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/todo/{userId}/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long userId, @PathVariable Long id) {
        todoService.deleteById(userId, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
