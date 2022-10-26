package com.mysite.sitebackend.board.news.controller;

import com.mysite.sitebackend.board.coin.domain.CoinBoard;
import com.mysite.sitebackend.board.coin.domain.CoinBoardComment;
import com.mysite.sitebackend.board.coin.dto.CoinBoardDto;
import com.mysite.sitebackend.board.dto.BoardInput;
import com.mysite.sitebackend.board.news.dao.NewsBoardRepository;
import com.mysite.sitebackend.board.news.domain.NewsBoard;
import com.mysite.sitebackend.board.news.domain.NewsBoardComment;
import com.mysite.sitebackend.board.news.dto.NewsBoardDto;
import com.mysite.sitebackend.board.news.dto.NewsBoardListDto;
import com.mysite.sitebackend.board.news.service.NewsBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/board/News")
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class NewsBoardController {
    private final NewsBoardService boardService;

    @RequestMapping("/")
    private String index(){
        System.out.println("News");
        return "News";
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

    //게시글 목록 불러오기
    @GetMapping("/get")
    private List<NewsBoardListDto> get(){
        return this.boardService.findAll();
    }
    //게시글 불러오기
    @GetMapping("/getid")
    public NewsBoardDto boardGet(@RequestBody BoardInput boardInput){
        return this.boardService.findById(boardInput);
    }
    //해당 id값의 댓글들 불러오기
    @GetMapping("/getid/comment")
    public List<NewsBoardComment> commentGet(@RequestBody BoardInput boardInput){
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
    public boolean commentDelete(@RequestBody BoardInput boardInput){
        return this.boardService.commnetDelete(boardInput);
    }
}
