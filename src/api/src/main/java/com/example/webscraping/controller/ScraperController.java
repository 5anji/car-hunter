package com.example.webscraping.controller;

import com.example.webscraping.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "/")//?????????
@RequiredArgsConstructor
public class ScraperController {
    private final CarService carService;

    @GetMapping(path = "/all")
    public void saveVehiclesFromAutoBid() throws IOException {
        carService.saveVehicles();
    }

}
