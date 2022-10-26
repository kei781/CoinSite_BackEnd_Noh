package com.mysite.sitebackend.board.coin.controller;


import com.mysite.sitebackend.board.coin.domain.CoinBoardComment;
import com.mysite.sitebackend.board.coin.dto.CoinBoardDto;
import com.mysite.sitebackend.board.coin.dto.CoinBoardListDto;
import com.mysite.sitebackend.board.coin.service.CoinBoardService;
import com.mysite.sitebackend.board.dto.BoardInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/board/coin")
@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
public class CoinBoardController {
    private final CoinBoardService boardService;

    @RequestMapping("/")
    public String index(){
        System.out.println("Coin");
        return "Coin";
    }

    //게시글 작성하기
    @PostMapping("/post")
    public boolean boardPost(@RequestBody BoardInput boardInput) {
        try {
            return this.boardService.boardPost(boardInput);
        } catch (Exception e) {
            return false;
        }
    }
    //댓글 작성하기
    @PostMapping("/post/comment")
    public boolean commentPost(@RequestBody BoardInput boardInput) {
        return this.boardService.commentPost(boardInput);
    }

    //게시글 전체 불러오기
    @GetMapping("/get")
    public List<CoinBoardListDto> get(){
        return this.boardService.findAll();
    }
    //게시글 1개 불러오기
    @GetMapping("/getid")
    public CoinBoardDto boardGet(@RequestBody BoardInput boardInput){
        return this.boardService.findByIdToBoard(boardInput);
    }
    //해당 id값의 댓글들 불러오기
    @GetMapping("/getid/comment")
    public List<CoinBoardComment> commentGet(@RequestBody BoardInput boardInput){
        return this.boardService.findByIdToComment(boardInput);
    }

    //게시글 수정
    @PatchMapping("/patch")
    public boolean boardPatch(@RequestBody BoardInput boardInput){
        return this.boardService.boardPatch(boardInput);
    }
    //댓글 수정
    @PatchMapping("/patch/comment")
    public boolean commentPatch(@RequestBody BoardInput boardInput){
        return this.boardService.commentPatch(boardInput);
    }

    //게시글 삭제
    @DeleteMapping("/delete")
    public boolean boardDelete(@RequestBody BoardInput boardInput){
        return this.boardService.boardDelete(boardInput);
    }
    //댓글삭제
    @DeleteMapping("/delete/comment")
    public boolean commnetDelete(@RequestBody BoardInput boardInput){
        return this.boardService.commnetDelete(boardInput);
    }

}
