package com.mysite.sitebackend.board.Inform.controller;

import com.mysite.sitebackend.board.Inform.dao.InformBoardRepository;
import com.mysite.sitebackend.board.Inform.domain.InformBoard;
import com.mysite.sitebackend.board.Inform.dto.InformBoardDto;
import com.mysite.sitebackend.board.Inform.dto.InfromBoardListDto;
import com.mysite.sitebackend.board.Inform.service.InformBoardService;
import com.mysite.sitebackend.board.coin.domain.CoinBoard;
import com.mysite.sitebackend.board.coin.dto.CoinBoardDto;
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
    public String post(@RequestBody BoardInput boardInput) {
        this.informBoardService.save(boardInput);
        return "성공적으로 저장되었습니다.";
    }
    //게시글 목록 불러오기
    @GetMapping("/get")
    @ResponseBody
    public List<InfromBoardListDto> get(){
        return this.informBoardService.findAll();
    }
    //게시글 불러오기
    @GetMapping("/getid")
    @ResponseBody
    public InformBoardDto getContnets(@RequestBody BoardInput boardInput){
        return this.informBoardService.findById(boardInput);
    }
    //게시글 수정하기
    @PatchMapping("/patch")
    @ResponseBody
    public InformBoardDto PatchContents(@RequestBody BoardInput boardInput){
        return this.informBoardService.findByIdToPatch(boardInput);
    }
    //게시글 삭제
    @DeleteMapping("/delete")
    @ResponseBody
    public String DeleteContents(@RequestBody BoardInput boardInput){
        return this.informBoardService.findByIdToDelete(boardInput);
    }
}
