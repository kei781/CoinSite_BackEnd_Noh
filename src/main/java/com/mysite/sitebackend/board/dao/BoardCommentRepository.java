package com.mysite.sitebackend.board.dao;

import com.mysite.sitebackend.board.domain.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Integer> {
    List<BoardComment> findAllByBoardIndex(Integer boardIndex);

    @Transactional
    void deleteAllByBoardIndex(Integer boardIndex);

    // 댓글 검색
    @Query("select b from BoardComment b where contents like %:value%")
    List<BoardComment> searchComment(String value);
}
