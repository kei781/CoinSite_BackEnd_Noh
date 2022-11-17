package com.mysite.sitebackend.board.dto;

import lombok.Data;

@Data
public class CommentListDto {
    private Integer id;
    private String contents; // 내용
    private String author; // 작성자명
    private String date; // 작성일자
    private Integer boardIndex;
}
