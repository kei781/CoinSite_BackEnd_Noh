package com.mysite.sitebackend.api.market.dao;

import com.mysite.sitebackend.api.market.domain.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepository extends JpaRepository<Market, Integer> {

}
