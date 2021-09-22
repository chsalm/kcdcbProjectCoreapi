package com.kcdcb.coreapi.repository;

import com.kcdcb.coreapi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
