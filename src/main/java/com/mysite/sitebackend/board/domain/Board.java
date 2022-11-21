package com.mysite.sitebackend.board.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 200)
    private String subject;
    @Lob
    private String contents;
    @Column(length = 20)
    private String author; // 작성자명
    @DateTimeFormat(pattern = "YYYYMMDD")
    private String date; // 작성일자
    private Integer views; // 조회수
    @Embedded
    private FileEntity image; // 이미지
    @Column(length = 20)
    private String lcategory; // 대카테고리
    @Column(length = 20)
    private String mcategory; // 중카테고리
}
