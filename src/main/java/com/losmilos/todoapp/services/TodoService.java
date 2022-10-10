package com.losmilos.todoapp.services;

import com.losmilos.todoapp.entity.Todo;
import com.losmilos.todoapp.entity.User;
import com.losmilos.todoapp.exception.NotFoundException;
import com.losmilos.todoapp.repository.TodoRepository;
import com.losmilos.todoapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TodoService {

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    public List<Todo> getAllForTodayFinished(Long userId) {
        return todoRepository.findAllForTodayFinishedByUserId(userId);
    }

    public Page<Todo> getPaged(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return todoRepository.findAllByUserId(userId, pageable);
    }

    public Todo create(Long userId, Todo todo) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found."));
        todo.setUser(user);
        return todoRepository.save(todo);
    }

    public Todo markAsFinished(Long userId, Long id) {
        Todo todo = todoRepository.findByIdAndUserId(id, userId).orElseThrow(() -> new NotFoundException("Todo not found."));
        todo.setFinished(true);
        return todoRepository.save(todo);
    }

    public Todo update(Long userId, Long id, Todo todoRequest) {
        Todo todo = todoRepository.findByIdAndUserId(id, userId).orElseThrow(() -> new NotFoundException("Todo not found."));
        todo.setName(todoRequest.getName());
        todo.setDescription(todoRequest.getDescription());
        todo.setDatetime(todoRequest.getDatetime());
        todo.setFinished(todoRequest.getFinished());
        return todoRepository.save(todo);
    }

    public void deleteById(Long userId, Long id) {
        Todo todo = todoRepository.findByIdAndUserId(id, userId).orElseThrow(() -> new NotFoundException("Todo not found."));
        todo.getUser().getTodos().remove(todo);
        todoRepository.delete(todo);
    }
}
