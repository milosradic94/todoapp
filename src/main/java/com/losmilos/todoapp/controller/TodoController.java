package com.losmilos.todoapp.controller;

import com.losmilos.todoapp.entity.Todo;
import com.losmilos.todoapp.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/todo")
    public ResponseEntity<Page<Todo>> getPaged(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(todoService.getPaged(page, size));
    }

    @PostMapping("/todo/create")
    public ResponseEntity<Todo> create(@RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.create(todo));
    }

    @PostMapping("/todo/{id}/finished")
    public ResponseEntity<Todo> markAsFinished(@PathVariable int id) {
        return ResponseEntity.ok(todoService.markAsFinished(id));
    }

    @PutMapping("/todo/update")
    public ResponseEntity<Todo> update(@RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.update(todo));
    }

    @DeleteMapping("/todo/{id}")
    public HttpStatus delete(@PathVariable int id) {
        todoService.deleteById(id);
        return HttpStatus.OK;
    }
}
