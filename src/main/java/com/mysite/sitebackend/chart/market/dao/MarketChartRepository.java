package com.mysite.sitebackend.chart.market.dao;

import com.mysite.sitebackend.board.coin.domain.CoinBoardComment;
import com.mysite.sitebackend.chart.market.domain.MarketChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

public interface MarketChartRepository extends JpaRepository<MarketChart, Integer> {
    MarketChart findByDateAndName(String date, String name);
}
