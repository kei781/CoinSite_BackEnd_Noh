package com.mysite.sitebackend.chart.stock.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String date; // basDt 날짜
    private String name; // itmsNm
    private String market; // mrktCtg
    private String close; // 종가
    private String open; // 시가
    private String high; // 고가
    private String low; // 저가
    private String fltRt; // 등락률
}
