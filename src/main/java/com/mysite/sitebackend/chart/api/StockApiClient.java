package com.mysite.sitebackend.chart.api;


import com.mysite.sitebackend.chart.dao.ChartRepository;
import com.mysite.sitebackend.chart.domain.Chart;
import lombok.RequiredArgsConstructor;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StockApiClient {
    private final ChartRepository chartRepository;
    LocalDate now = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    String formatedNow = now.minusDays(1).format(formatter);
    public String time(){
        DayOfWeek dayOfWeek = this.now.getDayOfWeek();
        int dayOfWeekNumber = dayOfWeek.getValue();
        String formatedNow1;
        //만약 오늘이 월요일이거나
        if(dayOfWeekNumber == 1){
            return formatedNow1 = this.now.minusDays(3).format(formatter);
        }
        //만약 오늘이 일요일이라면
        else if (dayOfWeekNumber ==7) {
            return formatedNow1 = this.now.minusDays(2).format(formatter);
        }
        return formatedNow1 = this.now.minusDays(1).format(formatter);
    }

    public Chart ApiCall(String name) throws Exception{
        Optional<Chart> opStockChart = Optional.ofNullable(this.chartRepository.findByDateAndNameAndChartIndex(this.formatedNow, name, "Stock"));
        if (opStockChart.isEmpty()) {
                //api에 데이터 요청하기
            StringBuffer sb = new StringBuffer("http://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo?");
            String idxNm = URLEncoder.encode(name, "UTF-8");
            sb.append("serviceKey=J0bSLK%2BDoFdhT9ULtidMBZ5nV2VMqf9Ly6LxAv0fzrVRoEOf62u4UbVmhHJZfFaDXbE53Bk%2FmY%2FRpTlNIC83ng%3D%3D");
            sb.append("&basDt=" + time());
            sb.append("&itmsNm=" + idxNm);

            URL url = new URL(sb.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type","application/xml");
            conn.connect();
            SAXBuilder builder = new SAXBuilder();


            Document document = builder.build(conn.getInputStream());
                //api 데이터 요청하기 끝

                //api 데이터 파싱하기
            Element root = document.getRootElement();
            Element response = root.getChild("response");
            Element body = root.getChild("body");
            Element items = body.getChild("items");
            Element item = items.getChild("item");
                //api 데이터 파싱하기 끝
            Optional<Element> opElement = Optional.ofNullable(item);
            Chart chart = new Chart();
            if (opElement.isPresent()){
                    //api 데이터 저장하기
                chart.setDate(this.formatedNow);//날짜
                chart.setName(item.getChildText("itmsNm")); // 이름
                chart.setValue(item.getChildText("clpr")); // 전일종가
                chart.setAvg(item.getChildText("fltRt")); // 전일대비 변동폭
                chart.setHigh(item.getChildText("hipr"));  // 전일 고점
                chart.setLow(item.getChildText("lopr")); // 전일 저점
                chart.setOpen(item.getChildText("mkp"));
                chart.setMarket(item.getChildText("mrktCtg"));
                chart.setChartIndex("Stock");
                this.chartRepository.save(chart);
            }
        }
        return this.chartRepository.findByDateAndNameAndChartIndex(this.formatedNow, name, "Stock");
    }
}
