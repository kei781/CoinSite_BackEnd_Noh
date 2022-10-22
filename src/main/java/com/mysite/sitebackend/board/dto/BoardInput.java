package com.mysite.sitebackend.board.dto;

import lombok.Getter;

@Getter
public class BoardInput {
    private Integer id;
    private String subject;
    private String contents;
    private String author;
}
