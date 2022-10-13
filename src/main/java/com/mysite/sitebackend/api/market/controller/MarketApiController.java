package com.mysite.sitebackend.api.market.controller;

import com.mysite.sitebackend.api.market.dto.MarketResponseDto;
import com.mysite.sitebackend.api.market.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/market")
@RequiredArgsConstructor
@RestController
@Controller
public class MarketApiController {
    private final MarketService marketService;


    @RequestMapping("")
    @ResponseBody
    public String index(){
        System.out.println("market");
        return "market";
    }

    @GetMapping("/{keyword}")
    public MarketResponseDto get(@PathVariable String keyword){
        return marketService.findByKeyword(keyword);
    }
}
