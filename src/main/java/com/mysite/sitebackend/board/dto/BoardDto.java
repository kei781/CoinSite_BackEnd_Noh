package com.mysite.sitebackend.board.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class BoardDto {
    private Integer id;
    private String subject;
    private String contents;
    private String author; // 작성자명
    private String date; // 작성일자
    private Integer views; // 조회수
    String lCategory; // 대카테고리
    String mCategory; // 중카테고리
}
