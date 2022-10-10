package com.losmilos.todoapp.repository;

import com.losmilos.todoapp.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query(value = "select * from todo t where t.user_id = :userId and t.finished = 0 and DATE(t.datetime) = CURDATE()", nativeQuery = true)
    List<Todo> findAllForTodayFinishedByUserId(@Param("userId") Long userId);

    Page<Todo> findAllByUserId(Long userId, Pageable pageable);

    Optional<Todo> findByIdAndUserId(Long id, Long userId);

}
