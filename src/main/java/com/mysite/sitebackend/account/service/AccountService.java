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
    public boolean signUp(String userId, String userName, String password, String confirmPassword, String gender){
        if(password.equals(confirmPassword)){
            Account a1 = new Account();
            a1.setUserId(userId);
            a1.setUserName(userName);
            a1.setPassword(password);
            a1.setGender(gender);
            this.accountRepository.save(a1);
            return true;
        }
        else{
            return false;
        }
    }
    //로그인
    public boolean signIn(String userId, String password){
        Optional<Account> opAccount = Optional.ofNullable(accountRepository.findByUserId(userId));
        if (opAccount.isPresent()){
            Account account = accountRepository.findByUserId(userId);
            if (account.getPassword().equals(password)){
                return true;
            }
            else {
                return false;
            }
        }
        else {
           return false;
        }
    }

    public Optional accountGet(String userId){
        Optional<Account> opAccount = Optional.ofNullable(accountRepository.findByUserId(userId));
        return opAccount;
    }
}
