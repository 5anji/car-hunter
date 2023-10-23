package com.example.webscraping.model.dto.response;

import com.example.webscraping.model.enums.SiteName;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.KebabCaseStrategy.class)
public class CarResponseDto {
    private String bodyType;
    private String title;
    private String registrationDate;
    private Integer millage;
    private Double displacement;
    private List<String> photoUrls;
    private String gearBox;
    private Integer price;
    private String auctionDate;
    private String url;
    private String unitOfMillage;
    private SiteName siteName;
}
