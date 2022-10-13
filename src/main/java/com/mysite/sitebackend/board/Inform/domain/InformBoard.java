package com.mysite.sitebackend.board.Inform.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class InformBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    private String contents;


}
