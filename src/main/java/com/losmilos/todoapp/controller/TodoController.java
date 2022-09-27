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

    @GetMapping("/todo")
    public ResponseEntity<Page<TodoResponse>> getPaged(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<Todo> todoPaged = todoService.getPaged(page, size);
        Page<TodoResponse> todoResponse = todoPaged.map(todo -> todoMapper.toResponse(todo));
        return new ResponseEntity<Page<TodoResponse>>(todoResponse, HttpStatus.OK);
    }

    @PostMapping("/todo/create")
    public ResponseEntity<TodoResponse> create(@Valid @RequestBody TodoRequest todoRequest) {
        Todo todo = todoService.create(todoMapper.toEntity(todoRequest));
        TodoResponse todoResponse = todoMapper.toResponse(todo);
        return new ResponseEntity<TodoResponse>(todoResponse, HttpStatus.CREATED);
    }

    @PostMapping("/todo/{id}/finished")
    public ResponseEntity<TodoResponse> markAsFinished(@PathVariable Long id) {
        Todo todo = todoService.markAsFinished(id);
        TodoResponse todoResponse = todoMapper.toResponse(todo);
        return new ResponseEntity<TodoResponse>(todoResponse, HttpStatus.OK);
    }

    @PutMapping("/todo/{id}/update")
    public ResponseEntity<TodoResponse> update(@PathVariable Long id, @Valid @RequestBody TodoRequest todoRequest) {
        Todo todo = todoService.update(id, todoMapper.toEntity(todoRequest));
        TodoResponse todoResponse = todoMapper.toResponse(todo);
        return new ResponseEntity<TodoResponse>(todoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        todoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
