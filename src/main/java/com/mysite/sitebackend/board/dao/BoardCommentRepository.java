package com.mysite.sitebackend.board.dao;

import com.mysite.sitebackend.board.domain.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Integer> {
    List<BoardComment> findAllByBoardIndex(Integer boardIndex);

    @Transactional
    void deleteAllByBoardIndex(Integer boardIndex);
}
