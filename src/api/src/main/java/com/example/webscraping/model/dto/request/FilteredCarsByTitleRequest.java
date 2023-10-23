package com.example.webscraping.model.dto.request;

import com.example.webscraping.model.enums.SiteName;
import lombok.*;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilteredCarsByTitleRequest {
    private String title;
    @Builder.Default
    private Integer page = 0;
    @Builder.Default
    private Integer perPage = 10;
    @Builder.Default
    private Integer minPrice = 0;
    @Builder.Default
    private Integer maxPrice = Integer.MAX_VALUE;
    @Builder.Default
    private Integer minMillage = 0;
    @Builder.Default
    private Integer maxMillage = Integer.MAX_VALUE;
    @Builder.Default
    private Double minDisplacement = 0.0;
    @Builder.Default
    private Double maxDisplacement = Double.MAX_VALUE;
    @Builder.Default
    private String field = "c.auction_date";
    @Builder.Default
    private SiteName siteName = SiteName.AUTOBID;
    @Builder.Default
    private Sort.Direction type = Sort.Direction.ASC;
}
