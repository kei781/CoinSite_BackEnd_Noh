package com.mysite.sitebackend.board.Inform.controller;

import com.mysite.sitebackend.board.Inform.service.InformBoardService;
import com.mysite.sitebackend.board.dto.BoardDto;
import com.mysite.sitebackend.board.dto.BoardInput;
import com.mysite.sitebackend.board.dto.BoardListDto;
import com.mysite.sitebackend.board.dto.CommentListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RequestMapping("/board/inform")
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class InformBoardController {
    private final InformBoardService boardService;

    @RequestMapping("/")
    public String Inform() {
        System.out.println("Infrom");
        return "Inform";
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

    //게시글 전체 불러오기
    @GetMapping("/get")
    public List<BoardListDto> get(){
        return this.boardService.findAll();
    }
    //게시글 1개 불러오기
    @GetMapping("/getid")
    public BoardDto boardGet(@RequestBody BoardInput boardInput){
        return this.boardService.findByIdToBoard(boardInput);
    }
    //해당 id값의 댓글들 불러오기
    @GetMapping("/getid/comment")
    public List<CommentListDto> commentGet(@RequestBody BoardInput boardInput){
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
    public boolean boardDelete(@RequestBody BoardInput boardInput) {
        return this.boardService.boardDelete(boardInput);
    }
    //댓글삭제
    @DeleteMapping("/delete/comment")
    public boolean commentDelete(@RequestBody BoardInput boardInput) {
        return this.boardService.commnetDelete(boardInput);
    }
}