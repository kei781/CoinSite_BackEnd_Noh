package com.mysite.sitebackend.board.Inform.dao;

import com.mysite.sitebackend.board.Inform.domain.InformBoardComment;
import com.mysite.sitebackend.board.coin.domain.CoinBoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InformBoardCommentRepository extends JpaRepository<InformBoardComment, Integer> {

    List<InformBoardComment> findAllByBoardIndex(Integer boardIndex);
    List<CoinBoardComment> deleteAllByBoardIndex(Integer boardIndex);
}
