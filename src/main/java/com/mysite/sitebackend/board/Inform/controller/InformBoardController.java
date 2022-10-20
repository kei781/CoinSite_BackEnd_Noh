package com.mysite.sitebackend.board.Inform.controller;

import com.mysite.sitebackend.board.Inform.dao.InformBoardRepository;
import com.mysite.sitebackend.board.Inform.domain.InformBoard;
import com.mysite.sitebackend.board.Inform.dto.InformBoardDto;
import com.mysite.sitebackend.board.Inform.dto.InfromBoardListDto;
import com.mysite.sitebackend.board.Inform.service.InformBoardService;
import com.mysite.sitebackend.board.coin.domain.CoinBoard;
import com.mysite.sitebackend.board.coin.dto.CoinBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/board/inform")
@Controller
@RequiredArgsConstructor
@CrossOrigin("*")
public class InformBoardController {
    private final InformBoardService informBoardService;

    @RequestMapping("/")
    @ResponseBody
    public String Inform(){
        System.out.println("Infrom");
        return "Inform";
    }
    //게시글 작성하기
    @PostMapping("/post")
    @ResponseBody
    public String post(@RequestParam("Subject") String subject, @RequestParam("Contents") String contents, @RequestParam("Author") String author) {
        this.informBoardService.save(subject, contents, author);
        return "성공적으로 저장되었습니다.";
    }
    //게시글 목록 불러오기
    @GetMapping("/get")
    @ResponseBody
    public List<InfromBoardListDto> get(){
        return this.informBoardService.findAll();
    }
    //게시글 불러오기
    @GetMapping("/get/{id}")
    @ResponseBody
    public InformBoardDto getContnets(@PathVariable("id") Integer id){
        return this.informBoardService.findById(id);
    }
    //게시글 수정하기
    @PatchMapping("/patch/{id}")
    @ResponseBody
    public InformBoardDto PatchContents(@PathVariable("id") Integer id, @RequestParam("Subject") String subject, @RequestParam("Contents") String contents, @RequestParam("Author") String author){
        return this.informBoardService.findByIdToPatch(id, subject, contents, author);
    }
    //게시글 삭제
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String DeleteContents(@PathVariable("id") Integer id){
        return this.informBoardService.findByIdToDelete(id);
    }
}
