package com.example.springsessionassignment.member.service;

import com.example.springsessionassignment.member.dto.MemberSaveRequestDto;
import com.example.springsessionassignment.member.dto.MemberSaveResponseDto;
import com.example.springsessionassignment.member.dto.MemberResponseDto;
import com.example.springsessionassignment.member.dto.MemberUpdateRequestDto;
import com.example.springsessionassignment.member.entity.Member;
import com.example.springsessionassignment.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAll() {
        List<Member> members = memberRepository.findAll();

//        List<MemberResponseDto> dtos = new ArrayList<>();
//        for (Member member : members) {
//            dtos.add(new MemberResponseDto(member.getId(), member.getEmail()));
//        }
//        return dtos;

        return members.stream()
                .map(member -> new MemberResponseDto(member.getId(), member.getEmail())).toList();
    }


    @Transactional(readOnly = true)
    public MemberResponseDto findById(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("그런 사람 없다니까!!!"));
        return new MemberResponseDto(member.getId(),member.getEmail());
    }

    @Transactional
    public void update(Long memberId, MemberUpdateRequestDto memberUpdateRequestDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("그런 사람 없다니까!!!"));
        member.update(memberUpdateRequestDto.getEmail());
    }

    @Transactional
    public void deleteById(Long memberId) {
       memberRepository.deleteById(memberId);
    }
}
