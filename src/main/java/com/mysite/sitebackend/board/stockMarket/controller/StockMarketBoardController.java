package com.mysite.sitebackend.board.stockMarket.controller;


import com.mysite.sitebackend.board.dto.BoardInput;

import com.mysite.sitebackend.board.stockMarket.domain.StockMarketBoardComment;
import com.mysite.sitebackend.board.stockMarket.dto.StockMarketBoardDto;
import com.mysite.sitebackend.board.stockMarket.dto.StockMarketBoardListDto;
import com.mysite.sitebackend.board.stockMarket.service.StockMarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/stockMarket")
@CrossOrigin("*")
public class StockMarketBoardController {
    private final StockMarketService boardService;

    @RequestMapping("/")
    private String index(){
        System.out.println("StockMarket");
        return "StockMarket";
    }

    //게시글 작성하기
    @PostMapping("/post")
    public boolean boardPost(@RequestBody BoardInput boardInput) {
        try {
            return this.boardService.boardPost(boardInput);
        } catch (SQLException e) {
            return false;
        }
    }
    //댓글 작성하기
    @PostMapping("/post/comment")
    public boolean commentPost(@RequestBody BoardInput boardInput) {
        return this.boardService.commentPost(boardInput);
    }

    //게시글 리스트 불러오기
    @GetMapping("/get")
    private List<StockMarketBoardListDto> get(){
        return this.boardService.findAll();
    }
    //게시글 불러오기
    @GetMapping("/getid")
    public StockMarketBoardDto boardGet(@RequestBody BoardInput boardInput){
        return this.boardService.findById(boardInput);
    }
    //해당 id값의 댓글들 불러오기
    @GetMapping("/getid/comment")
    public List<StockMarketBoardComment> commentGet(@RequestBody BoardInput boardInput){
        return this.boardService.findByIdToComment(boardInput);
    }

    //게시글 수정
    @PatchMapping("/patch")
    public boolean boardPatch(@RequestBody BoardInput boardInput){
        return this.boardService.boardPatch(boardInput);
    }
    //댓글 수정하기
    @PatchMapping("/patch/comment")
    public boolean commentPatch(@RequestBody BoardInput boardInput){
        return this.boardService.commentPatch(boardInput);
    }

    // 게시글 삭제
    @DeleteMapping("/delete")
    public boolean boardDelete(@RequestBody BoardInput boardInput){
        return this.boardService.boardDelete(boardInput);
    }
    //댓글삭제
    @DeleteMapping("/delete/comment")
    public boolean commentDelete(@RequestBody BoardInput boardInput) {
        return this.boardService.commnetDelete(boardInput);
    }

}
