package com.mysite.sitebackend.board.news.dao;


import com.mysite.sitebackend.board.news.domain.NewsBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsBoardRepository extends JpaRepository<NewsBoard, Integer> {
}
