package com.mysite.sitebackend.account.dto;


import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class AccountInput {
    private String userId;
    private String userName;
    private String password;
    private String confirmPassword;
}
