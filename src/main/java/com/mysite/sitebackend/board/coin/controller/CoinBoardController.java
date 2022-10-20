package com.mysite.sitebackend.board.coin.controller;


import com.mysite.sitebackend.board.coin.domain.CoinBoard;
import com.mysite.sitebackend.board.coin.dto.CoinBoardDto;
import com.mysite.sitebackend.board.coin.dto.CoinBoardListDto;
import com.mysite.sitebackend.board.coin.service.CoinBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/board/coin")
@RequiredArgsConstructor
@Controller
@CrossOrigin("*")
public class CoinBoardController {
    private final CoinBoardService coinBoardService;

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        System.out.println("Coin");
        return "Coin";
    }

    //게시글 작성하기
    @PostMapping("/post")
    @ResponseBody
    public String post(@RequestParam("Subject") String subject, @RequestParam("Contents") String contents, @RequestParam("Author") String author) {
        this.coinBoardService.save(subject, contents,  author);
        return "성공적으로 저장되었습니다.";
    }

    //게시글 전체 불러오기
    @GetMapping("/get")
    @ResponseBody
    public List<CoinBoardListDto> get(){
        return this.coinBoardService.findAll();
    }

    //게시글 1개 불러오기
    @GetMapping("/get/{id}")
    @ResponseBody
    public CoinBoardDto getContnets(@PathVariable("id") Integer id){
        return this.coinBoardService.findById(id);
    }

    //게시글 수정
    @PatchMapping("/patch/{id}")
    @ResponseBody
    public CoinBoardDto PatchContents(@PathVariable("id") Integer id, @RequestParam("Subject") String subject, @RequestParam("Contents") String contents, @RequestParam("Author") String author){
        return this.coinBoardService.findByIdToPatch(id, subject, contents, author);
    }

    //게시글 삭제
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String DeleteContents(@PathVariable("id") Integer id){
        return this.coinBoardService.findByIdToDelete(id);
    }

}
