package com.mysite.sitebackend.chart.market.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MarketChartResponseDto {
    private int display;
    private Item[] items;

    @Data
    static class Item{
        public LocalDate date;
        public String name;
        public float value;
    }
}
