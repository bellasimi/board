package com.example.board.board;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Reply {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private int seqReply;
    @Column(nullable = false)
    private int seqBoard;
    private String id;

    private String replytext;
    private String pwReply;
}
