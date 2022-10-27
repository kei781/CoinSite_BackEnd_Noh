package com.mysite.sitebackend.chart.market.controller;


import com.mysite.sitebackend.chart.market.domain.MarketChart;
import com.mysite.sitebackend.chart.market.service.MarketChartService;
import com.mysite.sitebackend.chart.market.vo.MarketApiVo;
import lombok.RequiredArgsConstructor;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
        System.out.println("정상실행되었씁니다.");
        return this.marketChartService.findByAll();
    }

    @GetMapping("/apitest")
    public void  apitest() throws Exception{
        StringBuffer sb = new StringBuffer("https://apis.data.go.kr/1160100/service/GetMarketIndexInfoService/getStockMarketIndex?");
        String name = URLEncoder.encode("코스피", "UTF-8");
        sb.append("ServiceKey=J0bSLK%2BDoFdhT9ULtidMBZ5nV2VMqf9Ly6LxAv0fzrVRoEOf62u4UbVmhHJZfFaDXbE53Bk%2FmY%2FRpTlNIC83ng%3D%3D");
        sb.append("&likeBasDt=20221011");
        sb.append("&idxNm="+name);

        URL url = new URL(sb.toString());

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Content-Type","application/xml");
        conn.connect();
        SAXBuilder builder = new SAXBuilder();

            Document document = builder.build(conn.getInputStream());

            Element root = document.getRootElement();
            Element response = root.getChild("response");
            Element body = response.getChild("body");
            Element items = body.getChild("items");
            List<Element> itemList = items.getChildren("item");
        MarketApiVo[] ar = new MarketApiVo[itemList.size()];

    }

}
