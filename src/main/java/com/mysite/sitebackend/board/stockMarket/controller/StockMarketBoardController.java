package com.mysite.sitebackend.board.stockMarket.controller;

import com.mysite.sitebackend.board.coin.domain.CoinBoard;
import com.mysite.sitebackend.board.stockMarket.dao.StockMarketBoardRepository;
import com.mysite.sitebackend.board.stockMarket.domain.StockMarketBoard;
import com.mysite.sitebackend.board.stockMarket.dto.StockMarketBoardListDto;
import com.mysite.sitebackend.board.stockMarket.service.StockMarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/stockMarket")
@CrossOrigin("*")
public class StockMarketBoardController {
    private final StockMarketService stockMarketService;

    @RequestMapping("/")
    @ResponseBody
    private String index(){
        System.out.println("StockMarket");
        return "StockMarket";
    }

    @PostMapping("/post")
    @ResponseBody
    public String post(@RequestParam("Subject") String subject, @RequestParam("Contents") String contents, @RequestParam("Author") String author) {
        this.stockMarketService.save(subject, contents, author);
        System.out.println("성공적으로 저장되었습니다.");
        return "성공적으로 저장되었습니다.";
    }

    @GetMapping("/get")
    @ResponseBody
    private List<StockMarketBoardListDto> get(){
        return this.stockMarketService.findAll();
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public StockMarketBoard getContnets(@PathVariable("id") Integer id){
        return this.stockMarketService.findById(id);
    }

}
