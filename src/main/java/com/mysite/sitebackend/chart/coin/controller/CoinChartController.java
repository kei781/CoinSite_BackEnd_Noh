package com.mysite.sitebackend.chart.coin.controller;


import com.mysite.sitebackend.chart.coin.domain.CoinChart;
import com.mysite.sitebackend.chart.coin.service.CoinChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/chart/coin")
@RequiredArgsConstructor
@Controller
public class CoinChartController {
    private  final CoinChartService coinChartService;
    @RequestMapping("/")
    @ResponseBody
    public String index(){
        System.out.println("coin_chart_index 연결");
        return "coin_chart";
    }
    @GetMapping("/get")
    @ResponseBody
    public List<CoinChart> get(){
        return this.coinChartService.findAll();
    }
}