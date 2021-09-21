package com.example.board.member;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {


    @Test
    public void getId(){
        final Member member =
                Member.builder()
                        .id("admin")
                        .pw("1234")
                        .name("admin")
                        .gender("여")
                        .tel("0101112222")
                        .build();
        final String id = member.getId();
        assertEquals("admin",id);
    }

}