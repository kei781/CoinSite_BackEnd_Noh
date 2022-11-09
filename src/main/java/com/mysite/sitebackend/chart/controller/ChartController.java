package com.mysite.sitebackend.chart.controller;


import com.mysite.sitebackend.chart.domain.Chart;
import com.mysite.sitebackend.chart.dto.MarketApiDto;
import com.mysite.sitebackend.chart.dto.StockApiDto;
import com.mysite.sitebackend.chart.service.ChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/chart")
@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
public class ChartController {
    private final ChartService chartService;

    @GetMapping("/")
    public String index() {
        System.out.println("MarketChart Index 호출됨");
        return "MarketChart";
    }

    @GetMapping("/getAll")
    public List<Chart> getAll() {
        return this.chartService.findAll();
    }

    //증시
    @GetMapping("/Market/get")
    public MarketApiDto marketGet(@RequestParam("name") String name) {
        return chartService.marketGet(name);
    }

    //주식
    @GetMapping("/Stock/get")
    public StockApiDto stockGet(@RequestParam("name") String name) {
        return chartService.stockGet(name);
    }

}
