package com.mysite.sitebackend.board.dao;

import com.mysite.sitebackend.board.domain.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepostiroy extends JpaRepository<FileEntity, Long> {
}
