package com.mysite.sitebackend.board.Inform.controller;

import com.mysite.sitebackend.board.Inform.domain.InformBoardComment;
import com.mysite.sitebackend.board.Inform.dto.InformBoardDto;
import com.mysite.sitebackend.board.Inform.dto.InfromBoardListDto;
import com.mysite.sitebackend.board.Inform.service.InformBoardService;
import com.mysite.sitebackend.board.dto.BoardInput;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public List<InfromBoardListDto> get() {
        return this.boardService.findAll();
    }
    //게시글 불러오기
    @GetMapping("/getid")
    public InformBoardDto boardGet(@RequestBody BoardInput boardInput) {
        return this.boardService.findById(boardInput);
    }
    //해당 id값의 댓글들 불러오기
    @GetMapping("/getid/comment")
    public List<InformBoardComment> commentGet(@RequestBody BoardInput boardInput) {
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