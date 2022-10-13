package com.mysite.sitebackend.board.Inform.controller;

import com.mysite.sitebackend.board.Inform.dao.InformBoardRepository;
import com.mysite.sitebackend.board.Inform.domain.InformBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/board/inform")
@Controller
@RequiredArgsConstructor
public class InformBoardController {
    private final InformBoardRepository informBoardRepository;

    @RequestMapping("")
    @ResponseBody
    public String Inform(){
        System.out.println("Infrom");
        return "Inform";
    }
    @RequestMapping("/post")
    @ResponseBody
    public String post(@RequestParam("Subject") String subject, @RequestParam("Contents") String contents) {
        InformBoard C1 = new InformBoard();
        C1.setSubject(subject);
        C1.setContents(contents);
        this.informBoardRepository.save(C1);
        System.out.println("성공적으로 저장되었습니다.");
        return "성공적으로 저장되었습니다.";
    }
    @GetMapping("/get")
    @ResponseBody
    public List<InformBoard> get(){
        return this.informBoardRepository.findAll();
    }

}
