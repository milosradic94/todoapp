package com.losmilos.todoapp.controller;

import com.losmilos.todoapp.dto.TodoDto;
import com.losmilos.todoapp.entity.Todo;
import com.losmilos.todoapp.services.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TodoController {

    private final TodoService todoService;
    private final ModelMapper modelMapper;

    public TodoController(TodoService todoService, ModelMapper modelMapper) {
        this.todoService = todoService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/todo")
    public ResponseEntity<Page<TodoDto>> getPaged(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<Todo> todoPaged = todoService.getPaged(page, size);
        Page<TodoDto> todoResponse = todoPaged.map(todo -> modelMapper.map(todo, TodoDto.class));
        return new ResponseEntity<Page<TodoDto>>(todoResponse, HttpStatus.OK);
    }

    @PostMapping("/todo/create")
    public ResponseEntity<TodoDto> create(@RequestBody TodoDto todoDto) {
        Todo todoRequest = modelMapper.map(todoDto, Todo.class);
        Todo todo = todoService.create(todoRequest);
        TodoDto todoResponse = modelMapper.map(todo, TodoDto.class);
        return new ResponseEntity<TodoDto>(todoResponse, HttpStatus.CREATED);
    }

    @PostMapping("/todo/{id}/finished")
    public ResponseEntity<TodoDto> markAsFinished(@PathVariable long id) {
        Todo todo = todoService.markAsFinished(id);
        TodoDto todoResponse = modelMapper.map(todo, TodoDto.class);
        return new ResponseEntity<TodoDto>(todoResponse, HttpStatus.OK);
    }

    @PutMapping("/todo/{id}/update")
    public ResponseEntity<TodoDto> update(@PathVariable long id, @RequestBody TodoDto todoDto) {
        Todo todoRequest = modelMapper.map(todoDto, Todo.class);
        Todo todo = todoService.update(id, todoRequest);
        TodoDto todoResponse = modelMapper.map(todo, TodoDto.class);
        return new ResponseEntity<TodoDto>(todoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        todoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
