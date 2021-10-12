package com.example.board.mypage;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class JPATest {
        @Autowired
        MypageRepository mypageRepository;

        @Test
        public void MypageTest() {
            log.info(mypageRepository.selectSQLById1(1).toString());
        }
}
