package com.example.webscraping.controller;

import com.example.webscraping.model.ResponseDTO;
import com.example.webscraping.service.ScraperService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(path = "/")
@RequiredArgsConstructor
public class ScraperController {

    private final ScraperService scraperService;

    @GetMapping(path = "/{vehicleModel}")
    public Set<ResponseDTO> getVehicleByModel(@PathVariable String vehicleModel) {
        return scraperService.getVehicleByModel(vehicleModel);
    }

}
