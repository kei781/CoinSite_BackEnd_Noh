package com.mysite.sitebackend.board.coin.domain;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.mysite.sitebackend.board.coin.domain.CoinBoard;
import javax.persistence.*;
import javax.persistence.ManyToOne;

@Data
@Entity
public class CoinBoardComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String contents; // 내용
    @Column(length = 20)
    private String author; // 작성자명
    @DateTimeFormat(pattern = "YYYYMMDD")
    private String date; // 작성일자
    @ManyToOne
    private CoinBoard coinBoard;
}
