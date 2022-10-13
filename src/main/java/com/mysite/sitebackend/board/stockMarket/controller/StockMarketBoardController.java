package com.mysite.sitebackend.board.stockMarket.controller;

import com.mysite.sitebackend.board.stockMarket.dao.StockMarketBoardRepository;
import com.mysite.sitebackend.board.stockMarket.domain.StockMarketBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/stockMarket")
public class StockMarketBoardController {
    private final StockMarketBoardRepository stockMarketBoardRepository;

    @RequestMapping("")
    @ResponseBody
    private String index(){
        System.out.println("StockMarket");
        return "StockMarket";
    }

    @PostMapping("/post")
    @ResponseBody
    private String post(@RequestParam("Subject") String subject, @RequestParam("Contents") String contents){
        StockMarketBoard c1 = new StockMarketBoard();
        c1.setSubject(subject);
        c1.setContents(contents);
        this.stockMarketBoardRepository.save(c1);
        System.out.println("성공적으로 저장되었습니다.");
        return "성공적으로 저장되었습니다.";
    }

    @GetMapping("/get")
    @ResponseBody
    private List<StockMarketBoard> get(){
        return this.stockMarketBoardRepository.findAll();
    }

}
