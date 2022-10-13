package com.mysite.sitebackend.board.coin.controller;


import com.mysite.sitebackend.board.coin.dao.CoinBoardRepository;
import com.mysite.sitebackend.board.coin.domain.CoinBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/board/coin")
@RequiredArgsConstructor
@Controller
public class CoinBoardController {
    private final CoinBoardRepository coinBoardRepository;

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        System.out.println("Coin");
        return "Coin";
    }


    @RequestMapping("/post")
    @ResponseBody
    public String post(@RequestParam("Subject") String subject, @RequestParam("Contents") String contents) {
        CoinBoard C1 = new CoinBoard();
        C1.setSubject(subject);
        C1.setContents(contents);
        this.coinBoardRepository.save(C1);
        System.out.println("성공적으로 저장되었습니다.");
        return "성공적으로 저장되었습니다.";
    }

    @GetMapping("/get")
    @ResponseBody
    public List<CoinBoard> get(){
        return this.coinBoardRepository.findAll();
    }

}
