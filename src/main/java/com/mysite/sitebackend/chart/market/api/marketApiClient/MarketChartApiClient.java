package com.mysite.sitebackend.chart.market.api.marketApiClient;

import com.mysite.sitebackend.api.market.dto.MarketResponseDto;
import com.mysite.sitebackend.chart.market.dao.MarketChartRepository;
import com.mysite.sitebackend.chart.market.dto.MarketChartResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class MarketChartApiClient {
//        @Autowired
//        private RestTemplate restTemplate = new RestTemplate();
//        private final MarketChartRepository c1 = new MarketChartRepository();
//
//        private final String openMarketUrl = "https://apis.data.go.kr/1160100/service/GetMarketIndexInfoService/getStockMarketIndex?";
//        private final String serviceKey = "J0bSLK%2BDoFdhT9ULtidMBZ5nV2VMqf9Ly6LxAv0fzrVRoEOf62u4UbVmhHJZfFaDXbE53Bk%2FmY%2FRpTlNIC83ng%3D%3D";
//        private final String resultType = "json";
//        private final String likeBastDt = "20221007";
//        private final String idxNm = "코스닥";

//    public MarketResponseDto requestMarket(String keyword) {
//        final HttpHeaders headers = new HttpHeaders();
//
//        headers.set("serviceKey", serviceKey);
//        headers.set("resultType", resultType);
//        headers.set("likeBastDt", likeBastDt);
//        headers.set("idxNm", idxNm);
////            headers.set("idxNm", keyword);
//        final HttpEntity<String> entity = new HttpEntity<>(headers);
//
//
//        return this.restTemplate.exchange(openMarketUrl, HttpMethod.GET, entity, MarketResponseDto.class).getBody();
//
//    }

//    public Object requestMarket(String keyword){
//        String url = "https://apis.data.go.kr/1160100/service/GetMarketIndexInfoService/getStockMarketIndex?serviceKey=J0bSLK%2BDoFdhT9ULtidMBZ5nV2VMqf9Ly6LxAv0fzrVRoEOf62u4UbVmhHJZfFaDXbE53Bk%2FmY%2FRpTlNIC83ng%3D%3D&resultType=json&likeBasDt=20221007&idxNm=코스피";
//        Object apidate = restTemplate.getForObject(url, String.class);
//        c1.setDate(apidate.response.body.items.item.basDt);
//        c1.setName(apidate.response.body.items.item.idxNm);
//        c1.setValue(apidate.response.body.items.item.clpr);
//
//
//        return ;
//    }

}
