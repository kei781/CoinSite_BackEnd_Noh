package com.mysite.sitebackend.account.service;


import com.mysite.sitebackend.account.dao.AccountRepository;
import com.mysite.sitebackend.account.domain.Account;
import com.mysite.sitebackend.board.coin.domain.CoinBoard;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    //회원가입
    public String signUp(String userId, String userName, String password, String confirmPassword, String gender){
        if(password.equals(confirmPassword)){
            Account a1 = new Account();
            a1.setUserId(userId);
            a1.setUserName(userName);
            a1.setPassword(password);
            a1.setGender(gender);
            this.accountRepository.save(a1);
            return "회원가입을 축하드립니다.";
        }
        else{
            return "패스워드가 다릅니다.";
        }
    }
    //로그인
    public String signIn(String userId, String password){
        Optional<Account> opAccount = Optional.ofNullable(accountRepository.findByUserId(userId));
        if (opAccount.isPresent()){
            Account account = accountRepository.findByUserId(userId);
            if (account.getPassword().equals(password)){
                return account.getUserId() + "님 방문을 환영합니다.";
            }
            else {
                return "내용을 재확인해주세요.";
            }
        }
        else {
           return "내용을 재확인해주세요.";
        }
    }
}
