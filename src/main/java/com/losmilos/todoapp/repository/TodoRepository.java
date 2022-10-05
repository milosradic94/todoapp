package com.losmilos.todoapp.repository;

import com.losmilos.todoapp.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    Page<Todo> findAllByUserId(Long userId, Pageable pageable);

    Optional<Todo> findByIdAndUserId(Long id, Long userId);

}
