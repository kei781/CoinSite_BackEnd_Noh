package com.mysite.sitebackend.chart.market.controller;


import com.mysite.sitebackend.board.vo.BoardInput;
import com.mysite.sitebackend.chart.market.domain.MarketChart;
import com.mysite.sitebackend.chart.market.dto.MarketApiVo;
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

//    @GetMapping("/get")
//    public List<MarketChart> get(){
//        System.out.println("정상실행되었씁니다.");
//        return this.marketChartService.findByAll();
//    }

    @GetMapping("/get")
    public MarketChart get(@RequestBody MarketApiVo marketApiVo) {
        return marketChartService.get(marketApiVo);
    }

}
