package com.example.springsessionassignment.auth.controller;

import com.example.springsessionassignment.auth.dto.AuthLoginRequestDto;
import com.example.springsessionassignment.auth.dto.AuthLoginResponseDto;
import com.example.springsessionassignment.auth.dto.AuthSignupRequestDto;
import com.example.springsessionassignment.auth.service.AuthService;
import com.example.springsessionassignment.common.consts.Const;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public void signup(@RequestBody AuthSignupRequestDto dto) {
        authService.signup(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthLoginRequestDto dto, HttpServletRequest request) {
        AuthLoginResponseDto result = authService.login(dto);
        HttpSession session = request.getSession(); // 신규 세션 생성, JSESSIONID 쿠키 발급
        session.setAttribute(Const.LOGIN_USER, result.getMemberId()); // 서버 메모리에 세션 저장

        return ResponseEntity.ok("로그인 성공");
    }
}
