package com.mysite.sitebackend.account.controller;


import com.mysite.sitebackend.account.domain.Account;
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
    public boolean signUp(@RequestParam("UserId") String userId, @RequestParam("UserName") String userName,
                         @RequestParam("Password") String password, @RequestParam("ConfirmPassword") String confirmPassword, @RequestParam("Gender") String gender){
                return this.accountService.signUp(userId, userName, password, confirmPassword, gender);
    }
    //로그인
    @PostMapping("/signIn")
    public boolean signIn(@RequestParam("UserId") String userId, @RequestParam("Password") String password){
        return this.accountService.signIn(userId, password);
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
    public Optional accountGet(@RequestParam("UserId")String userId){
        return this.accountService.accountGet(userId);
    }
}