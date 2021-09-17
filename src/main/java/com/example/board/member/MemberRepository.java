package com.example.board.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Integer> {
    List<Member> findByIdAndPw(String id, String pw);
    Member findByTel(String tel);

}