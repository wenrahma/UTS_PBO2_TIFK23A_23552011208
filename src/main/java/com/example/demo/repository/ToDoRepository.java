package com.example.demo.repository;

import com.example.demo.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {

    // Find completed todos
    List<ToDo> findByCompletedTrue();

    // Find not completed todos
    List<ToDo> findByCompletedFalse();
}
