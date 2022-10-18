package com.mysite.sitebackend.chart.market.controller;


import com.mysite.sitebackend.api.market.dto.MarketResponseDto;
import com.mysite.sitebackend.chart.market.domain.MarketChart;
import com.mysite.sitebackend.chart.market.service.MarketChartService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chart/market")
@RequiredArgsConstructor
public class MarketChartController {
    private final MarketChartService marketChartService;

    @GetMapping("/")
    @ResponseBody
    public String index(){
        System.out.println("MarketChart Index 호출됨");
        return "MarketChart";
    }

    @GetMapping("/get")
    @ResponseBody
    public List<MarketChart> get(){
        System.out.println("정상실행되었씁니다.");
        return this.marketChartService.findByAll();
    }

//    @GetMapping("/get/api/{keyword}")
//    @ResponseBody
//    public MarketResponseDto getApiByKeyword(@RequestParam String keyword){
//        return this.marketChartService.findByKeyword(keyword);
//    }

}
