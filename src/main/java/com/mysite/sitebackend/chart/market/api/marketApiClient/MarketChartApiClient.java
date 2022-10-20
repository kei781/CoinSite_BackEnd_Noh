package com.mysite.sitebackend.chart.market.api.marketApiClient;

import com.mysite.sitebackend.chart.market.dao.MarketChartRepository;
import com.mysite.sitebackend.chart.market.dto.MarketChartResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.*;

@RequiredArgsConstructor
@Service
public class MarketChartApiClient {
        private final MarketChartRepository marketChartRepository;
        private final RestTemplate restTemplate;
        private final String openMarketUrl = "https://apis.data.go.kr/1160100/service/GetMarketIndexInfoService/getStockMarketIndex?";
        private final String serviceKey = "J0bSLK%2BDoFdhT9ULtidMBZ5nV2VMqf9Ly6LxAv0fzrVRoEOf62u4UbVmhHJZfFaDXbE53Bk%2FmY%2FRpTlNIC83ng%3D%3D";
        private final String resultType = "json";
        private final String likeBastDt = "20221007";
        private final String idxNm = "코스닥";


    public void requestMarket(String keyword){
        String url = "https://apis.data.go.kr/1160100/service/GetMarketIndexInfoService/getStockMarketIndex?serviceKey=J0bSLK%2BDoFdhT9ULtidMBZ5nV2VMqf9Ly6LxAv0fzrVRoEOf62u4UbVmhHJZfFaDXbE53Bk%2FmY%2FRpTlNIC83ng%3D%3D&resultType=json&likeBasDt=20221007&idxNm=코스피";
        Object apidate = restTemplate.getForObject(url, Object.class);
        apidate.getClass();
        System.out.println(apidate);
    }

}
