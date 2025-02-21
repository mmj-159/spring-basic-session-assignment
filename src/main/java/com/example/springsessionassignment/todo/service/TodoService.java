package com.example.springsessionassignment.todo.service;

import com.example.springsessionassignment.member.entity.Member;
import com.example.springsessionassignment.member.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

    @Transactional
    public TodoSaveResponseDto save(Long memberId, TodoSaveRequestDto saveRequestDto) {

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("그런 멤버 없다고")
        );

        Todo todo = new Todo(
                saveRequestDto.getContent(),
                member
        );
        Todo savedTodo = todoRepository.save(todo);
        return new TodoSaveResponseDto(
                savedTodo.getId(),
                savedTodo.getContent(),
                member.getId(),
                member.getEmail()
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
    public TodoUpdateResponseDto update(Long memberId, Long todoId, TodoUpdateRequestDto updateRequestDto) {

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("그런 멤버 없다고")
        );

        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalArgumentException("그런 Todo는 없단다~")
        );

        if (!todo.getMember().getId().equals(member.getId())){
            throw new IllegalArgumentException("Todo 작성자가 아닌데 왜 하려고 함?");
        }

        todo.update(updateRequestDto.getContent());
        return new TodoUpdateResponseDto(
                todo.getId(),
                todo.getContent()
        );
    }

    @Transactional
    public void deleteById(Long memberId, Long todoId) {

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("그런 멤버 없다고")
        );

        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalArgumentException("그런 Todo는 없단다~")
        );

        if (!todo.getMember().getId().equals(member.getId())){
            throw new IllegalArgumentException("Todo 작성자가 아닌데 왜 하려고 함?");
        }

       todoRepository.deleteById(todoId);
    }
}
