package com.mysite.sitebackend.board.coin.dto;

import lombok.Data;

@Data
public class CoinBoardDto {
    private Integer id;
    private String subject;
    private String contents;
    private String author; // 작성자명
    private String date; // 작성일자
    private Integer views; // 조회수
}
