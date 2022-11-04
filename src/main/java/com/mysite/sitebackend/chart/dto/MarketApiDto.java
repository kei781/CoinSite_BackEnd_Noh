package com.mysite.sitebackend.chart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketApiDto {
    private Integer id;
    private String name; // idxNm 이름
    private String value; //clpr 전일종가
    private String avg; // fltRt 전일대비 변동폭
    private String high; // hipr 전일 고점
    private String low; // lopr 전일 저점
    private String date; // basDt 날짜
    private String yavg; // lsYrEdVsFltRg 52주 평균 변동폭
}
