package com.mysite.sitebackend.account.controller;


import com.mysite.sitebackend.account.service.AccountService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
@CrossOrigin("*")
public class AccountController {
    private final AccountService accountService;
    
    //회원가입
    @PostMapping("/signUp")
    public String signUp(@RequestParam("UserId") String userId, @RequestParam("UserName") String userName,
                         @RequestParam("Password") String password, @RequestParam("ConfirmPassword") String confirmPassword, @RequestParam("Gender") String gender){
                return this.accountService.signUp(userId, userName, password, confirmPassword, gender);
    }
    //로그인
    @PostMapping("/signIn")
    public String signIn(@RequestParam("UserId") String userId, @RequestParam("Password") String password){
        return this.accountService.signIn(userId, password);
    }
}