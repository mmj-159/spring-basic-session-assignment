package com.example.springsessionassignment.todo.repository;

import com.example.springsessionassignment.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
