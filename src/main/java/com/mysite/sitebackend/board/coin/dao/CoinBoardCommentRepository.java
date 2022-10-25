package com.mysite.sitebackend.board.coin.dao;

import com.mysite.sitebackend.board.coin.domain.CoinBoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoinBoardCommentRepository extends JpaRepository<CoinBoardComment, Integer> {
    List<CoinBoardComment> findAllByBoardIndex(Integer boardIndex);
    List<CoinBoardComment> deleteAllByBoardIndex(Integer boardIndex);
}
