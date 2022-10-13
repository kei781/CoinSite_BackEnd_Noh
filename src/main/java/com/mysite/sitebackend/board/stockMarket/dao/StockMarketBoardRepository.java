package com.mysite.sitebackend.board.stockMarket.dao;

import com.mysite.sitebackend.board.stockMarket.domain.StockMarketBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMarketBoardRepository extends JpaRepository<StockMarketBoard, Integer> {
}
