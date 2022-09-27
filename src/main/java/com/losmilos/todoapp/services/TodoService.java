package com.losmilos.todoapp.services;

import com.losmilos.todoapp.entity.Todo;
import com.losmilos.todoapp.exception.NotFoundException;
import com.losmilos.todoapp.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public Page<Todo> getPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return todoRepository.findAll(pageable);
    }

    public Todo create(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo markAsFinished(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new NotFoundException("Todo not found."));
        todo.setFinished(true);
        return todoRepository.save(todo);
    }

    public Todo update(Long id, Todo todoRequest) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new NotFoundException("Todo not found."));
        todo.setName(todoRequest.getName());
        todo.setDescription(todoRequest.getDescription());
        todo.setDatetime(todoRequest.getDatetime());
        todo.setFinished(todoRequest.getFinished());
        return todoRepository.save(todo);
    }

    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }
}
