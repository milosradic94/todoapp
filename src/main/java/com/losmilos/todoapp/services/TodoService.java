package com.losmilos.todoapp.services;

import com.losmilos.todoapp.entity.Todo;
import com.losmilos.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public Page<Todo> getPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return todoRepository.findAll(pageable);
    }

    public Todo create(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo markAsFinished(int id) {
        Optional<Todo> todoFound = todoRepository.findById(id);
        if(todoFound.isPresent()) {
            Todo todo = todoFound.get();
            todo.setFinished(true);
            return todoRepository.save(todo);
        } else {
            return null;
        }
    }

    public Todo update(Todo todo) {
        if(todoRepository.findById(todo.getId()).isPresent()) {
            return todoRepository.save(todo);
        } else {
            return null;
        }
    }

    public boolean deleteById(int id) {
        todoRepository.deleteById(id);
        return true;
    }
}
