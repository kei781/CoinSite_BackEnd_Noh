package com.mysite.sitebackend.account.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(unique = true)
    private String userId;
    @NotNull
    private String userName;
    @NotNull
    private String password;
    @NotNull
    private String role;
}
