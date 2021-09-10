package com.example.board.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue
    private int seqMember;
    @Column(nullable = false,unique = true)//unique는 고유값 설정
    private String id;
    @Column(nullable = false)
    private String pw;
    @Column(nullable = false)
    private String name;
    @Column
    private String gender;
    @Column(nullable = false,unique = true)
    private String tel;


}