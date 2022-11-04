package com.mysite.sitebackend.chart.service;

import com.mysite.sitebackend.chart.api.MarketApiClient;
import com.mysite.sitebackend.chart.api.StockApiClient;
import com.mysite.sitebackend.chart.dao.ChartRepository;
import com.mysite.sitebackend.chart.domain.Chart;
import com.mysite.sitebackend.chart.dto.MarketApiDto;
import com.mysite.sitebackend.chart.dto.StockApiDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChartService {
    private final ChartRepository chartRepository;
    private final MarketApiClient marketApiClient;
    private final StockApiClient stockApiClient;
    private final ModelMapper modelMapper;

    public List<Chart> findAll(){
        List<Chart> charts = this.chartRepository.findAll();
        return charts;
    }

    public MarketApiDto marketGet(String name){
        try {
            Chart chart = marketApiClient.ApiCall(name);
            MarketApiDto marketApiDto = modelMapper.map(chart, MarketApiDto.class);
            return marketApiDto;
        } catch (Exception e){
            System.out.println("에러발생!!!에러발생!!!에러발생!!!에러발생!!!에러발생!!!");
            return null;
        }
    }

    public StockApiDto stockGet(String name) {
        try {
            Chart chart = stockApiClient.ApiCall(name);
            StockApiDto stockApiDto = modelMapper.map(chart, StockApiDto.class);
            return stockApiDto;
        } catch (Exception e) {
            System.out.println("에러발생!!!에러발생!!!에러발생!!!에러발생!!!에러발생!!!");
            return null;
        }
    }
}
