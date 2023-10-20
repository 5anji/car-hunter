package com.example.webscraping.service;

import com.example.webscraping.model.ResponseDTO;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public interface CarService {
    void saveVehicles() throws IOException;
}
