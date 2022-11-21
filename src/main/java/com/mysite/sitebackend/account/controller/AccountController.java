package com.mysite.sitebackend.account.controller;


import com.mysite.sitebackend.account.dto.AccountSighInDto;
import com.mysite.sitebackend.account.vo.service.AccountService;
import com.mysite.sitebackend.account.vo.AccountInput;
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
    public boolean signUp(@RequestBody AccountInput accountInput) {
        return this.accountService.signUp(accountInput);
    }

    //로그인
    @PostMapping("/signIn")
    public AccountSighInDto signIn(@RequestBody AccountInput accountInput) {
        return this.accountService.signIn(accountInput);
    }

    //패스워드 재설정
    @PatchMapping("/pwReset")
    public boolean pwReset(@RequestBody AccountInput accountInput) {
        return this.accountService.pwReset(accountInput);
    }

    //회원탈퇴
    @DeleteMapping("/signOut")
    public boolean signOut(@RequestBody AccountInput accountInput) {
        return this.accountService.signOut(accountInput);
    }
}