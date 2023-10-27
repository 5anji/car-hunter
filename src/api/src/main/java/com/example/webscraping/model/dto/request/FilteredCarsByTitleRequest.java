package com.example.webscraping.model.dto.request;

import com.example.webscraping.model.enums.Source;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.KebabCaseStrategy.class)
public class FilteredCarsByTitleRequest {
    @Builder.Default
    private String search = "";
    @Builder.Default
    private Integer page = 0;
    @Builder.Default
    private Integer perPage = 10;
    @Builder.Default
    private Integer priceMin = 0;
    @Builder.Default
    private Integer priceMax = Integer.MAX_VALUE;
    @Builder.Default
    private Integer millageMin = 0;
    @Builder.Default
    private Integer millageMax = Integer.MAX_VALUE;
    @Builder.Default
    private Double displacementMin = 0.0;
    @Builder.Default
    private Double displacementMax = Double.MAX_VALUE;
    @Builder.Default
    private List<String> sourceList = Collections.emptyList();
    @Builder.Default
    private String sortBy = "auctionDate";
    @Builder.Default
    private Sort.Direction sort = Sort.Direction.ASC;
}
