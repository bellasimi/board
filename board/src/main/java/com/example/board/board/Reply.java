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
    private String seqReply;
    @Column(nullable = false)
    private String seqBoard;
    private String id;
    @Column(nullable = false)
    private String reply;
    private String pwReply;
}
