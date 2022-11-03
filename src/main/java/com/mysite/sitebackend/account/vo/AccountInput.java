package com.mysite.sitebackend.account.vo;


import lombok.Getter;

@Getter
public class AccountInput {
    private String userId;
    private String userName;
    private String password;
    private String confirmPassword;
}
