package com.example.webscraping.service.impl;

import com.example.webscraping.model.dbo.CarDbo;
import com.example.webscraping.model.dto.request.FilteredCarsByTitleRequest;
import com.example.webscraping.model.dto.response.CarResponseDto;
import com.example.webscraping.repository.CarRepository;
import com.example.webscraping.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public CarResponseDto getCarById(Long id) {
        CarDbo carDbo = carRepository.findById(id).orElseThrow();
        return convertFromDboIntoDto(carDbo);
    }

    @Override
    public Page<CarResponseDto> getCarByTitle(FilteredCarsByTitleRequest filter) {
        List<CarResponseDto> carResponseDtos = carRepository.findCarByTitle("%" + filter.getTitle() + "%",
                        PageRequest.of(filter.getPage(), filter.getPerPage(),
                                Sort.by(filter.getType(), filter.getField())),
                        filter.getMinPrice(), filter.getMaxPrice(),
                        filter.getMinMillage(), filter.getMaxMillage(),
                        filter.getMinDisplacement(), filter.getMaxDisplacement())
                .map(CarServiceImpl::convertFromDboIntoDto)
                .toList();
        return new PageImpl<>(carResponseDtos);
    }

    private static CarResponseDto convertFromDboIntoDto(CarDbo carDbo) {
        return CarResponseDto.builder()
                .title(carDbo.getTitle())
                .unitOfMillage(carDbo.getUnitOfMillage())
                .millage(carDbo.getMillage())
                .url(carDbo.getUrl())
                .price(carDbo.getPrice())
                .registrationDate(carDbo.getRegistrationDate().format(DateTimeFormatter.ofPattern("MMM yyyy")))
                .displacement(carDbo.getDisplacement())
                .auctionDate(carDbo.getAuctionDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")))
                .photoUrls(carDbo.getPhotoUrls())
                .gearBox(carDbo.getGearBox())
                .bodyType(carDbo.getBodyType())
                .siteName(carDbo.getSiteName())
                .build();
    }
}
