package com.example.springsessionassignment.todo.controller;

import com.example.springsessionassignment.common.consts.Const;
import com.example.springsessionassignment.todo.dto.*;
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
    public ResponseEntity<TodoSaveResponseDto> save(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @RequestBody TodoSaveRequestDto saveRequestDto
    ) {
       return ResponseEntity.ok(todoService.save(memberId, saveRequestDto));
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
    public ResponseEntity<TodoUpdateResponseDto> update(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long todoId, @RequestBody TodoUpdateRequestDto updateRequestDto) {
        return ResponseEntity.ok(todoService.update(memberId,todoId, updateRequestDto));
    }

    @DeleteMapping("/todos/{todoId}")
    public void deleteById(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long todoId
    ) {
        todoService.deleteById(memberId, todoId);
    }
}
