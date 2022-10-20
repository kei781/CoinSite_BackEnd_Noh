package com.mysite.sitebackend.board.Inform.controller;

import com.mysite.sitebackend.board.Inform.dao.InformBoardRepository;
import com.mysite.sitebackend.board.Inform.domain.InformBoard;
import com.mysite.sitebackend.board.Inform.dto.InformBoardDto;
import com.mysite.sitebackend.board.Inform.dto.InfromBoardListDto;
import com.mysite.sitebackend.board.Inform.service.InformBoardService;
import com.mysite.sitebackend.board.coin.domain.CoinBoard;
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

    @PostMapping("/post")
    @ResponseBody
    public String post(@RequestParam("Subject") String subject, @RequestParam("Contents") String contents, @RequestParam("Author") String author) {
        this.informBoardService.save(subject, contents, author);

        return "성공적으로 저장되었습니다.";
    }

    @GetMapping("/get")
    @ResponseBody
    public List<InfromBoardListDto> get(){
        return this.informBoardService.findAll();
    }
    @GetMapping("/get/{id}")
    @ResponseBody
    public InformBoardDto getContnets(@PathVariable("id") Integer id){
        return this.informBoardService.findById(id);
    }
}
