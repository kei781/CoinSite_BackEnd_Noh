package com.mysite.sitebackend.chart.market.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class MarketChart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String name;

    private Integer value;

    @DateTimeFormat(pattern = "YYYYMMDD")
    private LocalDate date;
}
