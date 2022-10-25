package com.mysite.sitebackend.board.news.domain;

import com.mysite.sitebackend.board.coin.domain.CoinBoardComment;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class NewsBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;
    private String contents;
    @Column(length = 20)
    private String author; // 작성자명
    @DateTimeFormat(pattern = "YYYYMMDD")
    private String date; // 작성일자
    private Integer views; // 조회수
}
