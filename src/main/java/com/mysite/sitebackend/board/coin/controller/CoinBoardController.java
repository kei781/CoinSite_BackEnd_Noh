package com.mysite.sitebackend.board.coin.controller;


import com.mysite.sitebackend.board.coin.domain.CoinBoard;
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
@Controller
@CrossOrigin("*")
public class CoinBoardController {
    private final CoinBoardService boardService;

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        System.out.println("Coin");
        return "Coin";
    }

    //게시글 작성하기
    @PostMapping("/post")
    @ResponseBody
    public String post(@RequestBody BoardInput boardInput) {
        this.boardService.boardPost(boardInput);
        return "성공적으로 저장되었습니다.";
    }

    //댓글 작성하기
    @PostMapping("/post/comment")
    @ResponseBody
    public boolean commentPost(@RequestBody BoardInput boardInput) {
        return this.boardService.commentPost(boardInput);
    }

    //게시글 전체 불러오기
    @GetMapping("/get")
    @ResponseBody
    public List<CoinBoardListDto> get(){
        return this.boardService.findAll();
    }

    //게시글 1개 불러오기
    @GetMapping("/getid")
    @ResponseBody
    public CoinBoardDto getContnets(@RequestBody BoardInput boardInput){
        return this.boardService.findByIdToBoard(boardInput);
    }

    //해당 id값의 댓글들 불러오기
    @GetMapping("/getid/comment")
    @ResponseBody
    public List<CoinBoardComment> getComment(@RequestBody BoardInput boardInput){
        return this.boardService.findByIdToComment(boardInput);
    }

    //게시글 수정
    @PatchMapping("/patch")
    @ResponseBody
    public CoinBoardDto patchContents(@RequestBody BoardInput boardInput){
        return this.boardService.findByIdToPatch(boardInput);
    }

    //게시글 삭제
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
