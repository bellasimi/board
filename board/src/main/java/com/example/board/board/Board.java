package com.example.board.board;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Board {
    @Id
    @GeneratedValue
    private int seqBoard;
    @Column(nullable = false)
    private String id;

    private String title;
    @Column(nullable = false)
    private String context;
    private String pwBoard;
    @Column(nullable = false)
    private String creDate;

}