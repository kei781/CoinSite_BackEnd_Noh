package com.mysite.sitebackend.board.dao;

import com.mysite.sitebackend.board.domain.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    List<Board> findAllByLcategoryAndMcategory(String lcategory, String mcategory);

    Board findByIdAndLcategoryAndMcategory(Integer id, String lcategory, String mcategory);

    // 종합검색
    @Query("select b from Board b where subject like %:value% or contents like %:value%")
    List<Board> searchAll(String value);

    // 게시판별 검색
    @Query("select b from Board b " +
            "where subject like %:value% or contents like %:value% " +
            "and (b.lcategory = :lcategory and b.mcategory = :mcategory)")
    List<Board> search(String value, String lcategory, String mcategory);


    @Query("select b from Board b " +
            "where b.lcategory = :lcategory and b.mcategory = :mcategory " +
            "order by b.date desc")
    List<Board> findThree(@Param("lcategory") String lcategory, @Param("mcategory") String mcategory, Pageable pageable);
}
