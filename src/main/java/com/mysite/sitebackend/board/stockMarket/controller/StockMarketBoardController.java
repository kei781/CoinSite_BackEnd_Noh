package com.mysite.sitebackend.board.stockMarket.controller;


import com.mysite.sitebackend.board.dto.BoardInput;

import com.mysite.sitebackend.board.stockMarket.domain.StockMarketBoardComment;
import com.mysite.sitebackend.board.stockMarket.dto.StockMarketBoardDto;
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
    private final StockMarketService boardService;

    @RequestMapping("/")
    @ResponseBody
    private String index(){
        System.out.println("StockMarket");
        return "StockMarket";
    }
    //게시글 작성하기
    @PostMapping("/post")
    @ResponseBody
    public String post(@RequestBody BoardInput boardInput) {
        this.boardService.save(boardInput);
        System.out.println("성공적으로 저장되었습니다.");
        return "성공적으로 저장되었습니다.";
    }

    //댓글 작성하기
    @PostMapping("/comment")
    @ResponseBody
    public boolean commentPost(@RequestBody BoardInput boardInput) {
        return this.boardService.commentPost(boardInput);
    }

    //게시글 리스트 불러오기
    @GetMapping("/get")
    @ResponseBody
    private List<StockMarketBoardListDto> get(){
        return this.boardService.findAll();
    }

    //게시글 불러오기
    @GetMapping("/getid")
    @ResponseBody
    public StockMarketBoardDto getContnets(@RequestBody BoardInput boardInput){
        return this.boardService.findById(boardInput);
    }

    //해당 id값의 댓글들 불러오기
    @GetMapping("/getid/comment")
    @ResponseBody
    public List<StockMarketBoardComment> getComment(@RequestBody BoardInput boardInput){
        return this.boardService.findByIdToComment(boardInput);
    }

    //게시글 수정
    @PatchMapping("/patch")
    @ResponseBody
    public StockMarketBoardDto PatchContents(@RequestBody BoardInput boardInput){
        return this.boardService.findByIdToPatch(boardInput);
    }
    // 게시글 삭제
    @DeleteMapping("/delete")
    @ResponseBody
    public String boardDelete(@RequestBody BoardInput boardInput){
        return this.boardService.boardDelete(boardInput);
    }
    //댓글삭제
    @DeleteMapping("/delete/comment")
    @ResponseBody
    public String deleteContents(@RequestBody BoardInput boardInput){
        return this.boardService.commnetDelete(boardInput);
    }

}
