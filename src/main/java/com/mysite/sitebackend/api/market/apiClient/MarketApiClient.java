package com.mysite.sitebackend.api.market.apiClient;


import com.mysite.sitebackend.api.market.dto.MarketResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@RequiredArgsConstructor
@Service
public class MarketApiClient {

    @Autowired
    private RestTemplate restTemplate;


    private final String serviceKey = "J0bSLK%2BDoFdhT9ULtidMBZ5nV2VMqf9Ly6LxAv0fzrVRoEOf62u4UbVmhHJZfFaDXbE53Bk%2FmY%2FRpTlNIC83ng%3D%3D";
    private final String openMarketUrl = "https://apis.data.go.kr/1160100/service/GetMarketIndexInfoService/getStockMarketIndex?";
    private final String resultType = "json";

    public MarketResponseDto requestMarket(String keyword){
        final HttpHeaders headers = new HttpHeaders();
        headers.set("serviceKey", serviceKey);
        headers.set("resultType", resultType);
        headers.set("idxNm", keyword);

        final HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(openMarketUrl, HttpMethod.GET, entity, MarketResponseDto.class).getBody();
    }


}
