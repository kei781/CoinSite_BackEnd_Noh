package com.mysite.sitebackend.chart.market.controller;


import com.mysite.sitebackend.chart.market.domain.MarketChart;
import com.mysite.sitebackend.chart.market.vo.MarketApiVo;
import com.mysite.sitebackend.chart.market.service.MarketChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chart/market")
@RequiredArgsConstructor
public class MarketChartController {
    private final MarketChartService marketChartService;

    @GetMapping("/")
    public String index(){
        System.out.println("MarketChart Index 호출됨");
        return "MarketChart";
    }

    @GetMapping("/get")
    public List<MarketChart> get(){
        return this.marketChartService.findByAll();
    }

    @GetMapping("/get1")
    public MarketChart get1(@RequestBody MarketApiVo marketApiVo) {
        return marketChartService.get(marketApiVo);
    }

}
