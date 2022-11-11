package com.mysite.sitebackend.account.dao;

import com.mysite.sitebackend.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUserId(String userId);

    void deleteAllByUserId(String userId);
}
