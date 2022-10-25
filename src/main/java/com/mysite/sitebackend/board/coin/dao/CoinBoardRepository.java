package com.mysite.sitebackend.board.coin.dao;

import com.mysite.sitebackend.board.coin.domain.CoinBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoinBoardRepository extends JpaRepository<CoinBoard, Integer> {
    CoinBoard findBySubject(String subject);
}
