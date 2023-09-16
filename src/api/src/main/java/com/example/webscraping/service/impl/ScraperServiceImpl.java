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
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class ScraperServiceImpl implements ScraperService {
    //Reading data from property file to a list
    @Value("#{'${website.urls}'.split(',')}")
    List<String> urls;


    public void test(Set<ResponseDTO> responseDTOS, String url) throws IOException {
        String apiKey = "b84fea938e623916c18c199a04817b5c";
        String scrapedUrl = "http://api.scraperapi.com?api_key=" + apiKey + "&url=" + url;
        Document document = Optional.of(Jsoup.connect(scrapedUrl).get()).orElseThrow();
        Element element = Optional.ofNullable(document.getElementById("content")).orElseThrow(IOException::new);
        Elements elements = Optional.of(element.getElementsByTag("a")).orElseThrow();
        HttpURLConnection httpURLConnection = (HttpURLConnection) document.connection();
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("X-MyHeader", "123");
        httpURLConnection.setRequestMethod("GET");
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

    }


    @Override
    public Set<ResponseDTO> getVehicleByModel(String vehicleModel) throws IOException {
        //Using a set here to only store unique elements
        Set<ResponseDTO> responseDTOS = new HashSet<>();
        //Traversing through the urls
        for (String url : urls) {

            if (url.contains("ikman")) {
                getVehicleByModelFromIkman(responseDTOS, url + vehicleModel);
            } else if (url.contains("riyasewana")) {
                getVehicleByModelFromRiyase(responseDTOS, url + vehicleModel);
            }
        }

        return responseDTOS;
    }

    @Override
    public void getVehicleByModelFromRiyase(Set<ResponseDTO> responseDTOS, String url) throws IOException {
        String apiKey = "b84fea938e623916c18c199a04817b5c";
        String scrapedUrl = "http://api.scraperapi.com?api_key=" + apiKey + "&url=" + url;
        Document document = Optional.of(Jsoup.connect(scrapedUrl).get()).orElseThrow();
        //Selecting the element which contains the ad list
        Element element = Optional.ofNullable(document.getElementById("content")).orElseThrow(IOException::new);
        //getting all the <a> tag elements inside the content div tag
        Elements elements = Optional.of(element.getElementsByTag("a")).orElseThrow();
        //traversing through the elements
        for (Element ads : elements) {
            ResponseDTO responseDTO = new ResponseDTO();

            if (StringUtils.hasText(ads.attr("title"))) {
                //mapping data to the model class
                responseDTO.setTitle(ads.attr("title"));
                responseDTO.setUrl(ads.attr("href"));
            }
            if (responseDTO.getUrl() != null) responseDTOS.add(responseDTO);

        }
    }

    @Override
    public void getVehicleByModelFromIkman(Set<ResponseDTO> responseDTOS, String url) throws IOException {
        String apiKey = "b84fea938e623916c18c199a04817b5c";
        String scrapedUrl = "http://api.scraperapi.com?api_key=" + apiKey + "&url=" + url;
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


}



