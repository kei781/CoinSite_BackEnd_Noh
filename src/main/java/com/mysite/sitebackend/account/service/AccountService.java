package com.mysite.sitebackend.account.service;


import com.mysite.sitebackend.account.dao.AccountRepository;
import com.mysite.sitebackend.account.domain.Account;
import com.mysite.sitebackend.account.dto.AccountSighInDto;
import com.mysite.sitebackend.account.vo.AccountInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    //회원가입
    public boolean signUp(AccountInput accountInput) {
        if (accountInput.getPassword().equals(accountInput.getConfirmPassword())) {
            Account a1 = new Account();
            a1.setUserId(accountInput.getUserId());
            a1.setUserName(accountInput.getUserName());
            a1.setPassword(accountInput.getPassword());
            a1.setRole("USER");
            this.accountRepository.save(a1);
            return true;
        } else return false;
    }

    //로그인
    public AccountSighInDto signIn(AccountInput accountInput) {
        // 아이디가 포함된 컬럼 찾음.(없다면 null)
        Optional<Account> opAccount = Optional.ofNullable(accountRepository.findByUserId(accountInput.getUserId()));
        AccountSighInDto accountSighInDto = new AccountSighInDto();
        // 만약 아이디가 포함된 칼럼이 '있다면'
        if (opAccount.isPresent()) {
            Account account = accountRepository.findByUserId(accountInput.getUserId());
            // 입력받은 패스워드와, 컬럼에 포함된 패스워드가 '같은지 체크'
            if (account.getPassword().equals(accountInput.getPassword())) {
                //로그인에 성공했을때만, 유저네임과 true 반환
                //실패했을땐 null과 false반환
                accountSighInDto.setUserId(opAccount.get().getUserId());
                accountSighInDto.setUserName(opAccount.get().getUserName());
                accountSighInDto.setAboolean(true);
                System.out.println(accountSighInDto);
                return accountSighInDto;
            } else {
                accountSighInDto.setUserId(null);
                accountSighInDto.setUserName(null);
                accountSighInDto.setAboolean(false);
                return accountSighInDto;
            }
        }
        // 만약 아이디가 포함된 칼럼이 '없다면'
        else {
            accountSighInDto.setUserId(null);
            accountSighInDto.setUserName(null);
            accountSighInDto.setAboolean(false);
            return accountSighInDto;
        }
    }

    //패스워드 리셋
    public boolean pwReset(AccountInput accountInput) {
        // 입력받은 패스워드와 체크패스워드가 같을때
        if (accountInput.getPassword().equals(accountInput.getConfirmPassword())) {
            Optional<Account> opAccount = Optional.ofNullable(this.accountRepository.findByUserId(accountInput.getUserId()));
            // 찾은 id가db에 존재할때
            if (opAccount.isPresent()) {
                Account account = this.accountRepository.findByUserId(accountInput.getUserId());
                account.setPassword(accountInput.getPassword());
                this.accountRepository.save(account);
                return true;
            } else return false;
        } else return false;
    }

    //회원 탈퇴
    public boolean signOut(AccountInput accountInput) {
        // 아이디가 포함된 컬럼 찾음.(없다면 null)
        Optional<Account> opAccount = Optional.ofNullable(accountRepository.findByUserId(accountInput.getUserId()));
        AccountSighInDto accountSighInDto = new AccountSighInDto();
        // 만약 아이디가 포함된 칼럼이 '있다면'
        if (opAccount.isPresent()) {
            Account account = accountRepository.findByUserId(accountInput.getUserId());
            // 입력받은 패스워드와, 컬럼에 포함된 패스워드가 '같은지 체크'
            if (account.getPassword().equals(accountInput.getPassword())) {
                //로그인에 성공했을때만, true 반환
                //실패했을땐 false반환
                accountRepository.deleteAllByUserId(accountInput.getUserId());
                return true;
            } else return false;
        }
        // 만약 아이디가 포함된 칼럼이 '없다면'
        else return false;
    }

}
