package com.mysite.sitebackend.api.market.service;

import com.mysite.sitebackend.api.market.apiClient.MarketApiClient;
import com.mysite.sitebackend.api.market.dto.MarketResponseDto;
import com.mysite.sitebackend.api.market.dao.MarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MarketService {
    private final MarketRepository marketRepository;
    private final MarketApiClient marketApiClient;

    @Transactional(readOnly = true)
    public MarketResponseDto findByKeyword(String keyword){
        return marketApiClient.requestMarket(keyword);
    }
}
