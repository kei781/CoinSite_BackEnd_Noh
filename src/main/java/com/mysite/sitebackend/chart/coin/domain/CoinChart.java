package com.mysite.sitebackend.chart.coin.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class CoinChart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private Integer value;

    @DateTimeFormat(pattern = "YYMMDD")
    private LocalDate date;

}
