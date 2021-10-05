package com.example.board.member;

import lombok.*;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

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
    @Column(nullable = false)
    private String email;
    @Column(nullable = false,unique = true)
    private String tel;



    @Builder
    public Member(String id, String pw,String name, String tel){
        Assert.hasText(id,"id를 입력하세요");
        Assert.hasText(pw,"pw를 입력하세요");
        Assert.hasText(tel,"tel을 입력하세요");
        this.id =id;
        this.pw = pw;
        this.name = name;
        this.tel = tel;
    }
}