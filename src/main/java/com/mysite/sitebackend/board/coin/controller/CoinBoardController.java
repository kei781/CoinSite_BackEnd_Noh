package com.mysite.sitebackend.board.coin.controller;


import com.mysite.sitebackend.board.coin.domain.CoinBoard;
import com.mysite.sitebackend.board.coin.dto.CoinBoardListDto;
import com.mysite.sitebackend.board.coin.service.CoinBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/board/coin")
@RequiredArgsConstructor
@Controller
public class CoinBoardController {
    private final CoinBoardService coinBoardService;

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        System.out.println("Coin");
        return "Coin";
    }


    @PostMapping("/post")
    @ResponseBody
    public String post(@RequestParam("Subject") String subject, @RequestParam("Contents") String contents, @RequestParam("Author") String author) {
        this.coinBoardService.save(subject, contents,  author);

        return "성공적으로 저장되었습니다.";
    }

    @GetMapping("/get")
    @ResponseBody
    public List<CoinBoardListDto> get(){
        return this.coinBoardService.findAll();
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public CoinBoard getContnets(@PathVariable("id") Integer id){
        return this.coinBoardService.findById(id);
    }
}
