package com.bina.az.binaazdata.dto.purchase.serviceDto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class BetweenPricesDto {

    @NotNull(message = "Minimum qiymət boş qoyula bilməz")
    @Min(value = 0, message = "Zəhmət olmasa keçərli qiymət daxil edin")
    Long minPrice;

    @NotNull(message = "Maximum qiymət boş qoyula bilməz")
    @Min(value = 0, message = "Zəhmət olmasa keçərli qiymət daxil edin")
    Long maxPrice;

}
