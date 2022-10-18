package com.mysite.sitebackend.board.news.controller;

import com.mysite.sitebackend.board.coin.domain.CoinBoard;
import com.mysite.sitebackend.board.news.dao.NewsBoardRepository;
import com.mysite.sitebackend.board.news.domain.NewsBoard;
import com.mysite.sitebackend.board.news.dto.NewsBoardListDto;
import com.mysite.sitebackend.board.news.service.NewsBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/board/News")
@Controller
@RequiredArgsConstructor
public class NewsBoardController {
    private final NewsBoardService newsBoardService;

    @RequestMapping("/")
    @ResponseBody
    private String index(){
        System.out.println("News");
        return "News";
    }

    @PostMapping("/post")
    @ResponseBody
    public String post(@RequestParam("Subject") String subject, @RequestParam("Contents") String contents, @RequestParam("Author") String author) {
        this.newsBoardService.save(subject, contents, author);
        System.out.println("성공적으로 저장되었습니다.");
        return "성공적으로 저장되었습니다.";
    }


    @GetMapping("/get")
    @ResponseBody
    private List<NewsBoardListDto> get(){
        return this.newsBoardService.findAll();
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public NewsBoard getContnets(@PathVariable("id") Integer id){
        return this.newsBoardService.findById(id);
    }
}
