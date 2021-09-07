package com.example.board.board;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply,String> {
   List<Reply> findBySeqBoard(int seqBoard);
}
