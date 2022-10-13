package com.mysite.sitebackend.api.market.controller;


import com.mysite.sitebackend.api.market.dao.MarketRepository;
import com.mysite.sitebackend.api.market.domain.Market;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RequestMapping("/market")
@Controller
@RequiredArgsConstructor
public class MarketController {

    private final MarketRepository marketRepository;

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        System.out.println("market");
        return "market";
    }


    @GetMapping("/createMarket")
    @ResponseBody
    public List<Market> createmarket() {
        Market m1 = new Market();
        m1.setName("코스피");
        m1.setDate(LocalDate.now());
        m1.setValue(2083);
        this.marketRepository.save(m1);  // 첫번째 질문 저장

        Market m2 = new Market();
        m2.setName("코스피");
        m2.setDate(LocalDate.now());
        m2.setValue(2083);
        this.marketRepository.save(m2);  // 첫번째 질문 저장

        return marketRepository.findAll();
    }

    @GetMapping("/get")
    @ResponseBody
    public List<Market> getMarket() {
        return marketRepository.findAll();
    }

}
