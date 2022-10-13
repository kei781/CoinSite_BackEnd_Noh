package com.mysite.sitebackend.board.Inform.dao;

import com.mysite.sitebackend.board.Inform.domain.InformBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformBoardRepository extends JpaRepository<InformBoard, Integer> {
}
