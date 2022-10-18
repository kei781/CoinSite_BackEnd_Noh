package com.mysite.sitebackend.chart.coin.service;


import com.mysite.sitebackend.chart.coin.dao.CoinChartRepository;
import com.mysite.sitebackend.chart.coin.domain.CoinChart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoinChartService {
    private CoinChartRepository coinChartRepository;

    public List<CoinChart> findAll(){
        return this.coinChartRepository.findAll();
    }
}
