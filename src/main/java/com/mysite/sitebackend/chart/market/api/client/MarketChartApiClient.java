package com.mysite.sitebackend.chart.market.api.client;

import com.mysite.sitebackend.chart.market.dao.MarketChartRepository;
import com.mysite.sitebackend.chart.market.domain.MarketChart;
import lombok.RequiredArgsConstructor;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class MarketChartApiClient {
        private final MarketChartRepository marketChartRepository;
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formatedNow = now.minusDays(1).format(formatter);

        public MarketChart marketApiCall(String market) throws Exception{
                if(market == "코스피" || market == "코스닥"){ // 코스피 코스닥만 입력가능
                        Optional<MarketChart> opMarketChart = Optional.ofNullable(marketChartRepository.findByDateAndName(formatedNow, market));
                        if (opMarketChart.isEmpty()) {
                                //api에 데이터 요청하기
                                StringBuffer sb = new StringBuffer("http://apis.data.go.kr/1160100/service/GetMarketIndexInfoService/getStockMarketIndex?");
                                String idxNm = URLEncoder.encode(market, "UTF-8");
                                sb.append("ServiceKey=J0bSLK%2BDoFdhT9ULtidMBZ5nV2VMqf9Ly6LxAv0fzrVRoEOf62u4UbVmhHJZfFaDXbE53Bk%2FmY%2FRpTlNIC83ng%3D%3D");
                                sb.append("&likeBasDt=" + formatedNow);
                                sb.append("&idxNm=" + idxNm);

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
                                MarketChart marketChart = new MarketChart();
                                if (opElement.isPresent()){ // 요청한 api에 데이터가 존재하면(전일이 평일일경우)
                                        //api 데이터 저장하기
                                        marketChart.setDate(item.getChildText("basDt"));//날짜
                                        marketChart.setName(item.getChildText("idxNm")); // 이름
                                        marketChart.setValue(item.getChildText("clpr")); // 전일종가
                                        marketChart.setAvg(item.getChildText("fltRt")); // 전일대비 변동폭
                                        marketChart.setHigh(item.getChildText("hipr"));  // 전일 고점
                                        marketChart.setLow(item.getChildText("lopr")); // 전일 저점
                                        marketChart.setYavg(item.getChildText("lsYrEdVsFltRg")); // 52주 평균 변동폭
                                }
                                else {
                                        formatedNow = now.minusDays(2).format(formatter);
                                        marketChart = marketChartRepository.findByDateAndName(formatedNow, market);
                                        marketChart.setDate(now.minusDays(1).format(formatter));
                                }
                                marketChartRepository.save(marketChart);
                        }
                        return marketChartRepository.findByDateAndName(formatedNow, market);
                }
                else { // 코스피 코스닥이 입력값이 아닐경우 안보냄.
                        return null;
                }

        }

}
