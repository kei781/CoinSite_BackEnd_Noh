package com.mysite.sitebackend.chart.market.service;

import com.mysite.sitebackend.chart.market.api.marketApiClient.MarketChartApiClient;
import com.mysite.sitebackend.chart.market.dao.MarketChartRepository;
import com.mysite.sitebackend.chart.market.domain.MarketChart;
import com.mysite.sitebackend.chart.market.dto.MarketApiVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketChartService {
    private final MarketChartRepository marketChartRepository;
    private final MarketChartApiClient marketChartApiClient;
//    public List<MarketChart> findByAll(){
//        return this.marketChartRepository.findAll();
//    }


    public MarketChart get(MarketApiVo marketApiVo){
        try {
            return marketChartApiClient.marketApiCall(marketApiVo.getName());
        }
        catch (Exception e){
            System.out.println("에러발생!!!에러발생!!!에러발생!!!에러발생!!!에러발생!!!");
            return null;
        }
    }
}
