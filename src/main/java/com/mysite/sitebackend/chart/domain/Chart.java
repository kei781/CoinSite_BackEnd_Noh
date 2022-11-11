package com.mysite.sitebackend.chart.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Chart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 200)
    private String name; // idxNm 이름
    private String value; //clpr 전일종가
    private String avg; // fltRt 전일대비 변동폭
    private String high; // hipr 전일 고점
    private String low; // lopr 전일 저점
    @DateTimeFormat(pattern = "YYYYMMDD")
    private String date; // basDt 날짜
    @Column(length = 20)
    private String chartIndex; // api구분

    //Market 만 해당
    private String yavg; // lsYrEdVsFltRg 52주 평균 변동폭
    //Stock 만 해당
    private String open; // 시가
    private String market; // mrktCtg // 시장구분
}