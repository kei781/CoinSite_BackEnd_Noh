package com.mysite.sitebackend.chart.api;

import com.mysite.sitebackend.chart.dao.ChartRepository;
import com.mysite.sitebackend.chart.domain.Chart;
import com.mysite.sitebackend.configurable.ApiKey;
import lombok.RequiredArgsConstructor;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class MarketApiClient {
    private final ChartRepository chartRepository;
    private final ApiKey apiKey;
    LocalDate now = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    String formatedNow = now.minusDays(1).format(formatter);

    private String time() {
        DayOfWeek dayOfWeek = this.now.getDayOfWeek();
        int dayOfWeekNumber = dayOfWeek.getValue();
        //만약 오늘이 월요일이거나
        if (dayOfWeekNumber == 1) return this.now.minusDays(3).format(formatter);
            //만약 오늘이 일요일이라면
        else if (dayOfWeekNumber == 7) return this.now.minusDays(2).format(formatter);
            //화요일~토요일이라면
        else return this.now.minusDays(1).format(formatter);
    }

    public Chart ApiCall(String name) throws Exception {
        if (name.equals("코스피") || name.equals("코스닥")) { // 코스피 코스닥만 입력가능
            // DB에 기저장된 자료가 있는지 체크
            // DB에 기 저장된 자료가 없다면 매일 초회에 한하여 1회 api호출 후 DB에 저장
            Optional<Chart> opMarketChart = Optional.ofNullable(this.chartRepository.findByDateAndNameAndChartIndex(this.formatedNow, name, "Market"));
            if (opMarketChart.isEmpty()) {
                //api에 데이터 요청하기
                StringBuilder sb = new StringBuilder("http://apis.data.go.kr/1160100/service/GetMarketIndexInfoService/getStockMarketIndex?");
                String idxNm = URLEncoder.encode(name, StandardCharsets.UTF_8);
                sb.append("serviceKey=").append(apiKey.getMarketApiKey());
                // 일, 월일경우 금요일껄 호출하여, 오늘데이터로 입력
                sb.append("&likeBasDt=").append(time());
                sb.append("&idxNm=").append(idxNm);

                URL url = new URL(sb.toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-Type", "application/xml");
                conn.connect();

                SAXBuilder builder = new SAXBuilder();

                Document document = builder.build(conn.getInputStream());
                //api 데이터 요청하기 끝

                //api 데이터 파싱하기
                Element root = document.getRootElement();
                Element body = root.getChild("body");
                Element items = body.getChild("items");
                Element item = items.getChild("item");
                //api 데이터 파싱하기 끝
                Optional<Element> opElement = Optional.ofNullable(item);
                Chart chartChart = new Chart();
                if (opElement.isPresent()) { // 요청한 api에 데이터가 존재하면(전일이 평일일경우)
                    //api 데이터 저장하기
                    chartChart.setDate(this.formatedNow);//날짜
                    chartChart.setName(item.getChildText("idxNm")); // 이름
                    chartChart.setValue(item.getChildText("clpr")); // 전일종가
                    chartChart.setAvg(item.getChildText("fltRt")); // 전일대비 변동폭
                    chartChart.setHigh(item.getChildText("hipr"));  // 전일 고점
                    chartChart.setLow(item.getChildText("lopr")); // 전일 저점
                    chartChart.setYavg(item.getChildText("lsYrEdVsFltRg")); // 52주 평균 변동폭
                    chartChart.setChartIndex("Market");
                    this.chartRepository.save(chartChart);
                }
            }
            // DB에 기 저장된 자료가 없다면 매일 초회에 한하여 1회 api호출 후 DB에 저장 후 return
            // DB에 기 저장된 자료가 있따면 api를 호출하지않고, DB에서 바로 return
            return this.chartRepository.findByDateAndNameAndChartIndex(formatedNow, name, "Market");
        }
        // 코스피 코스닥이 입력값이 아닐경우 안보냄.
        else return null;
    }
}






