package com.mysite.sitebackend.chart.market.api.marketApiClient;

import com.mysite.sitebackend.chart.market.dao.MarketChartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class MarketChartApiClient {
        private final MarketChartRepository marketChartRepository;
}
