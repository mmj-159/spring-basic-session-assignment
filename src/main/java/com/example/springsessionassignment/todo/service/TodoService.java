package com.example.springsessionassignment.todo.service;

import com.example.springsessionassignment.todo.dto.*;
import com.example.springsessionassignment.todo.entity.Todo;
import com.example.springsessionassignment.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public TodoSaveResponseDto save(TodoSaveRequestDto saveRequestDto) {
        Todo todo = new Todo(saveRequestDto.getContent());
        Todo savedTodo = todoRepository.save(todo);
        return new TodoSaveResponseDto(
                savedTodo.getId(),
                savedTodo.getContent()
        );
    }

    @Transactional(readOnly = true)
    public List<TodoResponseDto> findAll() {
        List<Todo> todos = todoRepository.findAll();
        List<TodoResponseDto> dtos = new ArrayList<>();
        for (Todo todo : todos) {
            dtos.add(new TodoResponseDto(
                    todo.getId(),
                    todo.getContent()
            ));
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public TodoResponseDto findById(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalArgumentException("그런 Todo는 없단다~")
        );
        return new TodoResponseDto(
                todo.getId(),
                todo.getContent()
        );
    }
    @Transactional
    public TodoUpdateResponseDto update(Long todoId, TodoUpdateRequestDto updateRequestDto) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalArgumentException("그런 Todo는 없단다~")
        );
        todo.update(updateRequestDto.getContent());
        return new TodoUpdateResponseDto(
                todo.getId(),
                todo.getContent()
        );
    }

    @Transactional
    public void deleteById(Long todoId) {
       if (!todoRepository.existsById(todoId)) {
            throw new IllegalArgumentException("존재하지 않는 todo랄까...");
        }
       todoRepository.deleteById(todoId);
    }
}
