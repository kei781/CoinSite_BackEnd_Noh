package com.mysite.sitebackend.account.service;


import com.mysite.sitebackend.account.dao.AccountRepository;
import com.mysite.sitebackend.account.domain.Account;
import com.mysite.sitebackend.account.dto.AccountInput;
import com.mysite.sitebackend.account.dto.AccountSighInDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    //회원가입
    public boolean signUp(AccountInput accountInput){
        //
        if(accountInput.getPassword().equals(accountInput.getConfirmPassword())){
            Account a1 = new Account();
            a1.setUserId(accountInput.getUserId());
            a1.setUserName(accountInput.getUserName());
            a1.setPassword(accountInput.getPassword());
            this.accountRepository.save(a1);
            return true;
        }
        else{
            return false;
        }
    }

    //로그인
    public AccountSighInDto signIn(AccountInput accountInput){
        Optional<Account> opAccount = Optional.ofNullable(accountRepository.findByUserId(accountInput.getUserId()));
        AccountSighInDto accountSighInDto = new AccountSighInDto();
        if (opAccount.isPresent()){
            Account account = accountRepository.findByUserId(accountInput.getUserId());
            if (account.getPassword().equals(accountInput.getPassword())){
                //로그인에 성공했을때만, 유저네임과 true 반환
                //실패했을땐 null과 false반환
                accountSighInDto.setUserName(opAccount.get().getUserName());
                accountSighInDto.setAboolean(true);
                System.out.println(accountSighInDto);
                return accountSighInDto;
            }
            else {
                accountSighInDto.setUserName(null);
                accountSighInDto.setAboolean(false);
                return accountSighInDto;
            }
        }
        else {
            accountSighInDto.setUserName(null);
            accountSighInDto.setAboolean(false);
            return accountSighInDto;
        }
    }

    public Optional accountGet(AccountInput accountInput){
        Optional<Account> opAccount = Optional.ofNullable(accountRepository.findByUserId(accountInput.getUserId()));
        return opAccount;
    }
}
