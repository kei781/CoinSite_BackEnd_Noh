package com.mysite.sitebackend.board.news.dao;

import com.mysite.sitebackend.board.coin.domain.CoinBoardComment;
import com.mysite.sitebackend.board.dto.CommentListDto;
import com.mysite.sitebackend.board.news.domain.NewsBoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsBoardCommentRepository extends JpaRepository<NewsBoardComment, Integer> {

    List<NewsBoardComment> findAllByBoardIndex(Integer boardIndex);
    List<NewsBoardComment> deleteAllByBoardIndex(Integer boardIndex);
}
