package com.mysite.sitebackend.board.Inform.controller;

import com.mysite.sitebackend.board.Inform.domain.InformBoardComment;
import com.mysite.sitebackend.board.Inform.dto.InformBoardDto;
import com.mysite.sitebackend.board.Inform.dto.InfromBoardListDto;
import com.mysite.sitebackend.board.Inform.service.InformBoardService;
import com.mysite.sitebackend.board.dto.BoardInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/board/inform")
@Controller
@RequiredArgsConstructor
@CrossOrigin("*")
public class InformBoardController {
    private final InformBoardService boardService;

    @RequestMapping("/")
    @ResponseBody
    public String Inform() {
        System.out.println("Infrom");
        return "Inform";
    }

    //게시글 작성하기
    @PostMapping("/post")
    @ResponseBody
    public String post(@RequestBody BoardInput boardInput) {
        this.boardService.save(boardInput);
        return "성공적으로 저장되었습니다.";
    }

    //댓글 작성하기
    @PostMapping("/comment")
    @ResponseBody
    public boolean commentPost(@RequestBody BoardInput boardInput) {
        return this.boardService.commentPost(boardInput);
    }

    //게시글 목록 불러오기
    @GetMapping("/get")
    @ResponseBody
    public List<InfromBoardListDto> get() {
        return this.boardService.findAll();
    }

    //게시글 불러오기
    @GetMapping("/getid")
    @ResponseBody
    public InformBoardDto getContnets(@RequestBody BoardInput boardInput) {
        return this.boardService.findById(boardInput);
    }

    //해당 id값의 댓글들 불러오기
    @GetMapping("/getid/comment")
    @ResponseBody
    public List<InformBoardComment> getComment(@RequestBody BoardInput boardInput) {
        return this.boardService.findByIdToComment(boardInput);
    }

    //게시글 수정하기
    @PatchMapping("/patch")
    @ResponseBody
    public InformBoardDto PatchContents(@RequestBody BoardInput boardInput) {
        return this.boardService.findByIdToPatch(boardInput);
    }

    // 게시글 삭제
    @DeleteMapping("/delete")
    @ResponseBody
    public String boardDelete(@RequestBody BoardInput boardInput) {
        return this.boardService.boardDelete(boardInput);
    }

    //댓글삭제
    @DeleteMapping("/delete/comment")
    @ResponseBody
    public String deleteContents(@RequestBody BoardInput boardInput) {
        return this.boardService.commnetDelete(boardInput);
    }
}