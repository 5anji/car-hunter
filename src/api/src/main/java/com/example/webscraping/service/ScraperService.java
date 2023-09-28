package com.example.webscraping.service;

import com.example.webscraping.model.ResponseDTO;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public interface ScraperService {
    Set<ResponseDTO> getVehicleByModel(String vehicleModel) throws IOException;

    void getVehicleByModelFromRiyase(Set<ResponseDTO> responseDTOS, String url, Map<String, String> header) throws IOException;

    void getVehicleByModelFromIkman(Set<ResponseDTO> responseDTOS, String url, Map<String, String> header) throws IOException;

    void getVehicleByModelFromAutobid(Set<ResponseDTO> responseDTOS, String url, @RequestHeader Map<String, String> header) throws IOException;
}
