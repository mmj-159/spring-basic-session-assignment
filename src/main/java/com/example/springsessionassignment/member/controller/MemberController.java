package com.example.springsessionassignment.member.controller;

import com.example.springsessionassignment.common.consts.Const;
import com.example.springsessionassignment.member.dto.MemberSaveRequestDto;
import com.example.springsessionassignment.member.dto.MemberSaveResponseDto;
import com.example.springsessionassignment.member.dto.MemberResponseDto;
import com.example.springsessionassignment.member.dto.MemberUpdateRequestDto;
import com.example.springsessionassignment.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<List<MemberResponseDto>> getAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<MemberResponseDto> getOne(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.findById(memberId));
    }

    @PutMapping("/members/{memberId}")
    public void update(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @RequestBody MemberUpdateRequestDto memberUpdateRequestDto) {
        memberService.update(memberId, memberUpdateRequestDto);
    }

    @DeleteMapping("/members/{memberId}")
    public void delete(@SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId) {
        memberService.deleteById(memberId);
    }
}
