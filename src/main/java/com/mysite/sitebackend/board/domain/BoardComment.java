package com.mysite.sitebackend.board.domain;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Data
@Entity
public class BoardComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String contents; // 내용
    @Column(length = 20)
    private String author; // 작성자명
    @DateTimeFormat(pattern = "YYYYMMDD")
    private String date; // 작성일자

    // 댓글이 달린 게시글을 찾기위한 기능
    private Integer boardIndex;
    @Column(length = 20)
    private String lcategory; // 대카테고리
    @Column(length = 20)
    private String mcategory; // 중카테고리
}
