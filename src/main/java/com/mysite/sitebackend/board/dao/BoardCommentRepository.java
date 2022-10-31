package com.mysite.sitebackend.board.dao;

import com.mysite.sitebackend.board.domain.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Integer> {
    List<BoardComment> findAllByBoardIndex(Integer boardIndex);
    List<BoardComment> deleteAllByBoardIndex(Integer boardIndex);
}
