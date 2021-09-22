package com.kcdcb.coreapi.service;

import com.kcdcb.coreapi.entity.Member;
import com.kcdcb.coreapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final MemberRepository memberRepository;

    public Member getMember(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        return member.get();
    }
    public List<Member> getMembers() {
        List<Member> allMember = memberRepository.findAll();
        return allMember;
    }
    @Transactional
    public Member registerMemeber(Member member) {
        return memberRepository.save(member);

    }
}
