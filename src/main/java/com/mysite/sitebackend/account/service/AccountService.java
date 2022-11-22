package com.mysite.sitebackend.account.service;


import com.mysite.sitebackend.account.dao.AccountRepository;
import com.mysite.sitebackend.account.domain.Account;
import com.mysite.sitebackend.account.dto.AccountSighInDto;
import com.mysite.sitebackend.account.kakaoclient.KakaoService;
import com.mysite.sitebackend.account.vo.AccountInput;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private KakaoService kakaoService;

    //회원가입
    public boolean signUp(AccountInput accountInput) {
        if (accountInput.getPassword().equals(accountInput.getConfirmPassword())) {
            Account a1 = new Account();
            a1.setUserId(accountInput.getUserId());
            a1.setUserName(accountInput.getUserName());
            a1.setPassword(this.passwordEncoder.encode(accountInput.getPassword()));
            a1.setRole("USER");
            this.accountRepository.save(a1);
            return true;
        } else return false;
    }

    //카카오 로그인
    public String getAccessToken(String authorize_code) {
        return this.kakaoService.getAccessToken(authorize_code);
    }

    public HashMap<String, Object> getUserInfo(String access_Token) {
        return this.kakaoService.getUserInfo(access_Token);
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
            if (this.passwordEncoder.matches(accountInput.getPassword(), account.getPassword())) {
                //로그인에 성공했을때만, 유저네임과 true 반환
                //실패했을땐 null과 false반환
                accountSighInDto.setUserId(opAccount.get().getUserId());
                accountSighInDto.setUserName(opAccount.get().getUserName());
                accountSighInDto.setAboolean(true);
                System.out.println(accountSighInDto);
            } else {
                accountSighInDto.setUserId(null);
                accountSighInDto.setUserName(null);
                accountSighInDto.setAboolean(false);
            }
            return accountSighInDto;
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
        if ((accountInput.getPassword().equals(accountInput.getConfirmPassword()))) {
            // 찾은 id가db에 존재할때
            Optional<Account> opAccount = Optional.ofNullable(this.accountRepository.findByUserId(accountInput.getUserId()));
            if (opAccount.isPresent()) {
                // 사용중인 패스워드랑, 입력받은 사용중인 패스워드의 값이 같을때
                if (this.passwordEncoder.matches(accountInput.getUsePassword(), opAccount.get().getPassword())) {
                    //패스워드 변경
                    Account account = this.accountRepository.findByUserId(accountInput.getUserId());
                    account.setPassword(passwordEncoder.encode(accountInput.getPassword()));
                    this.accountRepository.save(account);
                    return true;
                    //사용중인 패스워드랑, 입력받은 사용중인 패스워드의 값이 "다를때"
                } else return false;
                // 찾은 id가 db에 "없을때"
            } else return false;
            // 입력받은 패스워드와 체크패스워드가 "다를때"
        } else return false;
    }


    //회원 탈퇴
    public boolean signOut(AccountInput accountInput) {
        // 찾은 id가db에 존재할때
        Optional<Account> opAccount = Optional.ofNullable(this.accountRepository.findByUserId(accountInput.getUserId()));
        if (opAccount.isPresent()) {
            // 사용중인 패스워드랑, 입력받은 사용중인 패스워드의 값이 같을때
            if (this.passwordEncoder.matches(accountInput.getUsePassword(), opAccount.get().getPassword())) {
                //회원정보 삭제
                this.accountRepository.deleteById(opAccount.get().getId());
                return true;
                //사용중인 패스워드랑, 입력받은 사용중인 패스워드의 값이 "다를때"
            } else return false;
            // 찾은 id가 db에 "없을때"
        } else return false;
    }
}
