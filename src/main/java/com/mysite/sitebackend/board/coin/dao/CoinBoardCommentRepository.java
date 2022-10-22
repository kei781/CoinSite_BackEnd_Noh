package com.mysite.sitebackend.board.coin.dao;

import com.mysite.sitebackend.board.coin.domain.CoinBoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinBoardCommentRepository extends JpaRepository<CoinBoardComment, Integer> {


}
