package com.example.webscraping.service;

import com.example.webscraping.model.ResponseDTO;

import java.util.Set;

public interface ScraperService {
    Set<ResponseDTO> getVehicleByModel(String vehicleModel);
    void getVehicleByModelFromRiyase(Set<ResponseDTO> responseDTOS, String url);
    void getVehicleByModelFromIkman(Set<ResponseDTO> responseDTOS, String url);
}
