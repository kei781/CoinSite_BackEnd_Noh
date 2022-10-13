package com.mysite.sitebackend.board.news.controller;

import com.mysite.sitebackend.board.news.dao.NewsBoardRepository;
import com.mysite.sitebackend.board.news.domain.NewsBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/board/News")
@Controller
@RequiredArgsConstructor
public class NewsBoardController {
    private final NewsBoardRepository newsBoardRepository;

    @RequestMapping("")
    @ResponseBody
    private String index(){
        System.out.println("News");
        return "News";
    }

    @PostMapping("/post")
    @ResponseBody
    private String post(@RequestParam("Subject") String subject, @RequestParam("Contnets") String contents){
        NewsBoard c1 = new NewsBoard();
        c1.setSubject(subject);
        c1.setContents(contents);
        this.newsBoardRepository.save(c1);
        System.out.println("성공적으로 저장되었습니다.");
        return "성공적으로 저장되었습니다.";
    }


    @GetMapping("/get")
    @ResponseBody
    private List<NewsBoard> get(){
        return this.newsBoardRepository.findAll();
    }
}
