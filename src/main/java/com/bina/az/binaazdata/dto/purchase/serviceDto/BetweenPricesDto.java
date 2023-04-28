package com.bina.az.binaazdata.dto.purchase.serviceDto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class BetweenPricesDto {

//    @NotEmpty(message = "Minimum qiymət boş qoyula bilməz")
    @Size(min = 0 ,message = "Zəhmət olmasa keçərli qiymət daxil edin")
    Long minPrice;

//    @NotEmpty(message = "Maximum qiymət boş qoyula bilməz")
    @Size(min = 0 ,message = "Zəhmət olmasa keçərli qiymət daxil edin")
    Long maxPrice;

}
