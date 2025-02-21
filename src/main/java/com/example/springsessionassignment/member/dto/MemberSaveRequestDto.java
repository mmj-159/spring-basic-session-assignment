package com.example.springsessionassignment.member.dto;

import lombok.Getter;

@Getter
public class MemberSaveRequestDto {

    private String email;

    // 아무런 생성자가 없을 때는 기본적으로 기본생성자가 추가 되기 때문에 아~~무것도 안붙여도 됨
}
