package com.mysite.sitebackend.board.stockMarket.dao;

import com.mysite.sitebackend.board.coin.domain.CoinBoardComment;
import com.mysite.sitebackend.board.stockMarket.domain.StockMarketBoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockMarketBoardCommentRepository extends JpaRepository<StockMarketBoardComment, Integer> {
    List<StockMarketBoardComment> findAllByBoardIndex(Integer boardIndex);
    List<CoinBoardComment> deleteAllByBoardIndex(Integer boardIndex);
}
