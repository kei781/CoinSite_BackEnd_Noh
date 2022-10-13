package com.mysite.sitebackend.api.market.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String name;

    @Column(length = 200)
    private int value;

    @DateTimeFormat(pattern = "YYMMDD")
    private LocalDate date;
}
