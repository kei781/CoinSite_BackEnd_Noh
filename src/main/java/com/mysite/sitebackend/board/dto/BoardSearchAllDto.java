package com.mysite.sitebackend.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardSearchAllDto {
    private Integer id;
    private String subject;
    private String contents;
    private String author; // 작성자명
    private String date; // 작성일자
    private Integer views; // 조회수
    private String lCategory; // 대카테고리
    private String mCategory; // 중카테고리
}
