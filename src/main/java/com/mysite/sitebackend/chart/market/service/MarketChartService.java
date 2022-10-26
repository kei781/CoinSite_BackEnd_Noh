package com.mysite.sitebackend.chart.market.service;

import com.mysite.sitebackend.chart.market.dao.MarketChartRepository;
import com.mysite.sitebackend.chart.market.domain.MarketChart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketChartService {
    private final MarketChartRepository marketChartRepository;
    public List<MarketChart> findByAll(){
        return this.marketChartRepository.findAll();
    }





}
