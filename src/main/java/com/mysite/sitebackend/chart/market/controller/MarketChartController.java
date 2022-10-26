package com.mysite.sitebackend.chart.market.controller;


import com.mysite.sitebackend.chart.market.domain.MarketChart;
import com.mysite.sitebackend.chart.market.service.MarketChartService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
    public String  apitest(){
        StringBuffer result = new StringBuffer();
        String jsonPrintString = null;
        try {
            String apiUrl = "http://apis.data.go.kr/1160100/service/GetMarketIndexInfoService/getStockMarketIndex?"+
                    "serviceKey=J0bSLK%2BDoFdhT9ULtidMBZ5nV2VMqf9Ly6LxAv0fzrVRoEOf62u4UbVmhHJZfFaDXbE53Bk%2FmY%2FRpTlNIC83ng%3D%3D"+
                     "&likeBasDt=20221007" +  "&idxNm=%EC%BD%94%EC%8A%A4%EB%8B%A5";
            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));
            String returnLine;
            while((returnLine = bufferedReader.readLine()) != null) {
                result.append(returnLine);
            }

            JSONObject jsonObject = XML.toJSONObject(result.toString());
            jsonPrintString = jsonObject.toString();

            urlConnection.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonPrintString;
    }

}
