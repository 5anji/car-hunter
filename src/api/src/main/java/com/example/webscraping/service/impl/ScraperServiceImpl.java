package com.example.webscraping.service.impl;

import com.example.webscraping.model.ResponseDTO;
import com.example.webscraping.service.ScraperService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.IntStream;


@Service
@RequiredArgsConstructor
public class ScraperServiceImpl implements ScraperService {
    //Reading data from property file to a list
    @Value("#{'${website.urls}'.split(',')}")
    List<String> urls;

    @Override
    public Set<ResponseDTO> getVehicleByModel() throws IOException {
        Set<ResponseDTO> responseDTOS = new HashSet<>();
        for (String url : urls) {

//            if (url.contains("ikman")) {
//                getVehicleByModelFromIkman(responseDTOS, url + vehicleModel, Map.of("X-POWERED-BY", "Spring Framework 6"));
//            } else
//            if (url.contains("riyasewana")) {
//                getVehicleByModelFromRiyase(responseDTOS, url + vehicleModel, Map.of("X-POWERED-BY", "Spring Framework 6"));
//            }
            if (url.contains("autobid")) {
                getVehicleByModelFromAutobid(responseDTOS, url, Map.of("X-POWERED-BY", "Spring Framework 6"));
            }
        }

        return responseDTOS;
    }

    @Override
    public void getVehicleByModelFromRiyase(Set<ResponseDTO> responseDTOS, String url, @RequestHeader Map<String, String> header) throws IOException {
        String apiKey = "b84fea938e623916c18c199a04817b5c";
        String scrapedUrl = "http://api.scraperapi.com?api_key=" + apiKey + "&url=" + url + "&keep_headers=true&country_code=us";
        URL url1 = new URL(scrapedUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0"); //make rotation
        httpURLConnection.setRequestProperty("X-MyHeader", "123");
        httpURLConnection.setRequestProperty("Referer", "https://www.google.com/");// referer
        httpURLConnection.setRequestMethod("GET");
        Document document = Optional.of(Jsoup.connect(scrapedUrl).get()).orElseThrow();
        Element element = Optional.ofNullable(document.getElementById("content")).orElseThrow(IOException::new);
        Elements elements = Optional.of(element.getElementsByTag("a")).orElseThrow();

        //
        Element total = Optional.of(element.getElementsByClass("pagination")).orElseThrow(IOException::new).first();
        Integer totalPageNumber = null;
        if (total != null) {
            totalPageNumber = Integer.parseInt(total.child(total.childrenSize() - 3).text());
        }

        List<Integer> pagesList = IntStream.rangeClosed(2, totalPageNumber)
                .boxed()
                .toList();
        //

        if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            for (Element ads : elements) {
                ResponseDTO responseDTO = new ResponseDTO();
                if (StringUtils.hasText(ads.attr("title"))) {
                    responseDTO.setTitle(ads.attr("title"));
                    responseDTO.setUrl(ads.attr("href"));
                }
                if (responseDTO.getUrl() != null) responseDTOS.add(responseDTO);
            }
        }

        for (Integer i : pagesList) {
            String scrapedUrlPageing = "http://api.scraperapi.com?api_key=" + apiKey + "&url=" + url + "?page=" + i + "&keep_headers=true&country_code=us";
            URL url1paging = new URL(scrapedUrlPageing);
            HttpURLConnection httpURLConnection1 = (HttpURLConnection) url1paging.openConnection();
            httpURLConnection1.setRequestProperty("Content-Type", "application/json");
            httpURLConnection1.setRequestProperty("User-Agent", "Mozilla/5.0"); //make rotation
            httpURLConnection1.setRequestProperty("X-MyHeader", "123");
            httpURLConnection1.setRequestProperty("Referer", "https://www.google.com/");// referer
            httpURLConnection1.setRequestMethod("GET");
            Document document1 = Optional.of(Jsoup.connect(scrapedUrlPageing).get()).orElseThrow();
            Element element1 = Optional.ofNullable(document1.getElementById("content")).orElseThrow(IOException::new);
            Elements elements1 = Optional.of(element1.getElementsByTag("a")).orElseThrow();

            if (httpURLConnection1.getResponseCode() == HttpURLConnection.HTTP_OK) {
                for (Element ads : elements1) {
                    ResponseDTO responseDTO = new ResponseDTO();
                    if (StringUtils.hasText(ads.attr("title"))) {
                        responseDTO.setTitle(ads.attr("title"));
                        responseDTO.setUrl(ads.attr("href"));
                    }
                    if (responseDTO.getUrl() != null) responseDTOS.add(responseDTO);
                }
            }
        }

    }

    @Override
    public void getVehicleByModelFromIkman(Set<ResponseDTO> responseDTOS, String url, @RequestHeader Map<String, String> header) throws IOException {
        String apiKey = "b84fea938e623916c18c199a04817b5c";
        String scrapedUrl = "http://api.scraperapi.com?api_key=" + apiKey + "&url=" + url + "&keep_headers=true&country_code=us";
        URL url1 = new URL(scrapedUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0"); //make rotation
        httpURLConnection.setRequestProperty("X-MyHeader", "123");
        httpURLConnection.setRequestProperty("Referer", "https://www.google.com/");// referer
        httpURLConnection.setRequestMethod("GET");
        Document document = Optional.of(Jsoup.connect(scrapedUrl).get()).orElseThrow(IOException::new);
        Element element = Optional.ofNullable(document.getElementsByClass("list--3NxGO").first()).orElseThrow();
        //getting all the <a> tag elements inside the list-       -3NxGO class
        Elements elements = element.getElementsByTag("a");

        for (Element ads : elements) {

            ResponseDTO responseDTO = new ResponseDTO();

            if (StringUtils.hasText(ads.attr("href"))) {
                //mapping data to our model class
                responseDTO.setTitle(ads.attr("title"));
                responseDTO.setUrl("https://ikman.lk" + ads.attr("href"));
            }
            if (responseDTO.getUrl() != null) responseDTOS.add(responseDTO);

        }

    }

    @Override
    public void getVehicleByModelFromAutobid(Set<ResponseDTO> responseDTOS, String url, @RequestHeader Map<String, String> header) throws IOException {
        String apiKey = "b84fea938e623916c18c199a04817b5c";
        String scrapedUrl = "http://api.scraperapi.com?api_key=" + apiKey + "&url=" + url + "&keep_headers=true&country_code=us";
        URL url1 = new URL(scrapedUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0"); //make rotation
        httpURLConnection.setRequestProperty("X-MyHeader", "123");
        httpURLConnection.setRequestProperty("Referer", "https://www.google.com/");// referer
        httpURLConnection.setRequestMethod("GET");
        Document document = Optional.of(Jsoup.connect(scrapedUrl).get()).orElseThrow(IOException::new);
        Element element = Optional.ofNullable(document.getElementById("p")).orElseThrow();
        Elements elements1 = Optional.of(document.getElementsByClass("csc-default")).orElseThrow();
        Element elements2 = Optional.ofNullable(document.getElementById("placeholder_content")).orElseThrow();
        Elements elements3 = Optional.of(document.select("div.ui-tabs.ui-widget.ui-widget-content.ui-corner-all")).orElseThrow();
        Elements elementsT = Optional.of(document.select("div.auctionType_t.atc_4")).orElseThrow();
        Elements elementst = Optional.of(document.getElementsByClass("auctionType_t atc_4")).orElseThrow();


        System.out.println();

//        for (Element element : elements) {
//            ResponseDTO responseDTO = new ResponseDTO();
//            responseDTO.setTitle(element.getElementsByClass("carName js-car-name").text());
//            responseDTO.setUrl("https://autobid.de/?action=car&show=details&id=" + element.id().replace("car_", ""));
//
//            if (responseDTO.getUrl() != null) responseDTOS.add(responseDTO);
//
//        }

    }

}



