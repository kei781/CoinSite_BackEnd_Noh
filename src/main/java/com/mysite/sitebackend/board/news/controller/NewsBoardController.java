package com.mysite.sitebackend.board.news.controller;

import com.mysite.sitebackend.board.coin.domain.CoinBoard;
import com.mysite.sitebackend.board.coin.dto.CoinBoardDto;
import com.mysite.sitebackend.board.dto.BoardInput;
import com.mysite.sitebackend.board.news.dao.NewsBoardRepository;
import com.mysite.sitebackend.board.news.domain.NewsBoard;
import com.mysite.sitebackend.board.news.dto.NewsBoardDto;
import com.mysite.sitebackend.board.news.dto.NewsBoardListDto;
import com.mysite.sitebackend.board.news.service.NewsBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/board/News")
@Controller
@RequiredArgsConstructor
@CrossOrigin("*")
public class NewsBoardController {
    private final NewsBoardService newsBoardService;

    @RequestMapping("/")
    @ResponseBody
    private String index(){
        System.out.println("News");
        return "News";
    }
    //게시글 작성하기
    @PostMapping("/post")
    @ResponseBody
    public String post(@RequestBody BoardInput boardInput) {
        this.newsBoardService.save(boardInput);
        System.out.println("성공적으로 저장되었습니다.");
        return "성공적으로 저장되었습니다.";
    }

    //게시글 목록 불러오기
    @GetMapping("/get")
    @ResponseBody
    private List<NewsBoardListDto> get(){
        return this.newsBoardService.findAll();
    }
    //게시글 불러오기
    @GetMapping("/getid")
    @ResponseBody
    public NewsBoardDto getContnets(@RequestBody BoardInput boardInput){
        return this.newsBoardService.findById(boardInput);
    }
    //게시글 수정하기
    @PatchMapping("/patch")
    @ResponseBody
    public NewsBoardDto PatchContents(@RequestBody BoardInput boardInput){
        return this.newsBoardService.findByIdToPatch(boardInput);
    }
    //게시글 삭제
    @DeleteMapping("/delete")
    @ResponseBody
    public String DeleteContents(@RequestBody BoardInput boardInput){
        return this.newsBoardService.findByIdToDelete(boardInput);
    }
}
