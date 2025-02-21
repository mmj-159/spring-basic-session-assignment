package com.example.springsessionassignment.todo.controller;

import com.example.springsessionassignment.todo.dto.*;
import com.example.springsessionassignment.todo.entity.Todo;
import com.example.springsessionassignment.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    public ResponseEntity<TodoSaveResponseDto> save(@RequestBody TodoSaveRequestDto saveRequestDto) {
       return ResponseEntity.ok(todoService.save(saveRequestDto));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TodoResponseDto>> getAll() {
        return ResponseEntity.ok(todoService.findAll());
    }

    @GetMapping("/todos/{todoId}")
    public ResponseEntity<TodoResponseDto> getOne(@PathVariable Long todoId) {
        return ResponseEntity.ok(todoService.findById(todoId));
    }

    @PutMapping("/todos/{todoId}")
    public ResponseEntity<TodoUpdateResponseDto> update(@PathVariable Long todoId, @RequestBody TodoUpdateRequestDto updateRequestDto) {
        return ResponseEntity.ok(todoService.update(todoId, updateRequestDto));
    }

    @DeleteMapping("/todos/{todoId}")
    public void deleteById(@PathVariable Long todoId) {
        todoService.deleteById(todoId);
    }
}
