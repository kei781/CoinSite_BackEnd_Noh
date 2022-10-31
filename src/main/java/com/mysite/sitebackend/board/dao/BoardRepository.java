package com.mysite.sitebackend.board.dao;

import com.mysite.sitebackend.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    Board findBySubject(String subject);
    List<Board> findAllByLcategoryAndMcategory(String lcategory, String mcategory);
    Board findByIdAndLcategoryAndMcategory(Integer id, String lcategory, String mcategory);
}
