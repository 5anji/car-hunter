package com.example.webscraping.controller;

import com.example.webscraping.model.ResponseDTO;
import com.example.webscraping.service.ScraperService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Set;

@RestController
@RequestMapping(path = "/")
@RequiredArgsConstructor
public class ScraperController {
    private final ScraperService scraperService;

    @GetMapping(path = "/all")
    public Set<ResponseDTO> getVehicleByModel() throws IOException {
        return scraperService.getVehicleByModel();
    }

}
