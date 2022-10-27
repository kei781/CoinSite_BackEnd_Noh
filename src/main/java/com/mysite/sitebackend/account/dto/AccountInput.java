package com.mysite.sitebackend.account.dto;


import lombok.Getter;

@Getter
public class AccountInput {
    private String userId;
    private String userName;
    private String password;
    private String confirmPassword;
}
