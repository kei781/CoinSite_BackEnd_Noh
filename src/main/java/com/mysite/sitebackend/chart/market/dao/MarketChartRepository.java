package com.mysite.sitebackend.chart.market.dao;

import com.mysite.sitebackend.chart.market.domain.MarketChart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketChartRepository extends JpaRepository<MarketChart, Integer> {
}
