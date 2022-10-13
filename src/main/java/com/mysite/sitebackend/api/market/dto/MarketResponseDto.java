package com.mysite.sitebackend.api.market.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MarketResponseDto {
    private int display;
    private Item[] items;

    @Data
    static class Item{
        public LocalDate basDt;
        public String idxNm;
        public String idxCsf;
        public int epyItmsCnt;
        public float clpr;
        public float vs;
        public float fltRt;
        public float mkp;
        public float hipr;
        public float lopr;
        public int trqu;
        public int trPrc;
        public int lstgMrktTotAmt;
        public float lsYrEdVsFltRg;
        public float lsYrEdVsFltRt;
        public float yrWRcrdHgst;
        public LocalDate yrWRcrdHgstDt;
        public float yrWRcrdLwst;
        public LocalDate yrWRcrdLwstDt;
        public LocalDate basPntm;
        public int basIdx;
    }
}
