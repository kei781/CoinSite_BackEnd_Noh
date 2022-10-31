package com.mysite.sitebackend.board.dto;

import lombok.Data;

@Data
public class BoardDto {
    private Integer id;
    private String subject;
    private String contents;
    private String author; // 작성자명
    private String date; // 작성일자
    private Integer views; // 조회수
}
