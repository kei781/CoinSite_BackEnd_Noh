package com.mysite.sitebackend.chart.coin.dao;

import com.mysite.sitebackend.chart.coin.domain.CoinChart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinChartRepository extends JpaRepository<CoinChart, Integer> {
}
