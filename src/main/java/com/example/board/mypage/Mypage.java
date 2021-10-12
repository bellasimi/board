package com.example.board.mypage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Mypage {

    @Id
    @GeneratedValue
    private int mypageSeq;
    @Column(nullable = false)
    private String id;
    @Column(nullable = false)
    private String seqBoard;

}
