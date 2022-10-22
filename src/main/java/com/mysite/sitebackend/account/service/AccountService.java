package com.mysite.sitebackend.account.service;


import com.mysite.sitebackend.account.dao.AccountRepository;
import com.mysite.sitebackend.account.domain.Account;
import com.mysite.sitebackend.account.dto.UserInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    //회원가입
    public boolean signUp(UserInput userInput){
        if(userInput.getPassword().equals(userInput.getConfirmPassword())){
            Account a1 = new Account();
            a1.setUserId(userInput.getUserId());
            a1.setUserName(userInput.getUserName());
            a1.setPassword(userInput.getPassword());
            this.accountRepository.save(a1);
            return true;
        }
        else{
            return false;
        }
    }

    //로그인
    public boolean signIn(UserInput userInput){
        Optional<Account> opAccount = Optional.ofNullable(accountRepository.findByUserId(userInput.getUserId()));
        if (opAccount.isPresent()){
            Account account = accountRepository.findByUserId(userInput.getUserId());
            if (account.getPassword().equals(userInput.getPassword())){
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

    public Optional accountGet(UserInput userInput){
        Optional<Account> opAccount = Optional.ofNullable(accountRepository.findByUserId(userInput.getUserId()));
        return opAccount;
    }
}
