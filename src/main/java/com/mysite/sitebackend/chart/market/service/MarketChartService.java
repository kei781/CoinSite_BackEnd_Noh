package com.mysite.sitebackend.chart.market.service;

import com.mysite.sitebackend.api.market.apiClient.MarketApiClient;
import com.mysite.sitebackend.api.market.dto.MarketResponseDto;
import com.mysite.sitebackend.board.coin.domain.CoinBoard;
import com.mysite.sitebackend.chart.market.api.marketApiClient.MarketChartApiClient;
import com.mysite.sitebackend.chart.market.dao.MarketChartRepository;
import com.mysite.sitebackend.chart.market.domain.MarketChart;
import com.mysite.sitebackend.chart.market.dto.MarketChartResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketChartService {
    private final MarketChartRepository marketChartRepository;
    private final MarketChartApiClient marketChartApiClient;

    public List<MarketChart> findByAll(){
        return this.marketChartRepository.findAll();
    }

//    @Transactional(readOnly = true)
//    public MarketResponseDto findByKeyword(String keyword){
//        Object chart = marketChartApiClient.requestMarket(keyword);
//        MarketChartResponseDto c1 = new MarketChartResponseDto();
//
//
//        return ;
//    }




}
