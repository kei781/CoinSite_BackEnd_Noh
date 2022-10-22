package com.mysite.sitebackend.account.controller;


import com.mysite.sitebackend.account.dto.UserInput;
import com.mysite.sitebackend.account.service.AccountService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
@CrossOrigin("*")
public class AccountController {
    private final AccountService accountService;

    //회원가입
    @PostMapping("/signUp")
    public boolean signUp(@RequestBody UserInput userInput){
                return this.accountService.signUp(userInput);
    }
    //로그인
    @PostMapping("/signIn")
    public boolean signIn(@RequestBody UserInput userInput){
        return this.accountService.signIn(userInput);
    }
    //아이디 찾기
    @GetMapping("/findId")
    public String findId(){
        return "";
    }
    //패스워드 찾기
    @PostMapping("/forgotPassword")
    public String forgotPassword(){
        return "";
    }

    @GetMapping("/accountGet")
    public Optional accountGet(@RequestBody UserInput userInput){
        return this.accountService.accountGet(userInput);
    }
}