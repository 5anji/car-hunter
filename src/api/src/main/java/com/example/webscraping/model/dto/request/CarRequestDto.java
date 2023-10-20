package com.example.webscraping.model.dto.request;

import lombok.*;

import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarRequestDto {
    private String bodyType;
    private String title;
    private YearMonth dateOfRegistration;
    private Integer millage;
    private Double engineCapacity;
    private List<String> photoUrls;
    private String gearBox;
    private Integer price;
    private ZonedDateTime auctionDate;
    private String url;
    private String unitOfMillage;
}