package com.bina.az.binaazdata.dto.purchase.serviceDto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class GeneralServiceDto {
    @NotEmpty(message = "Məkan boş qoyula bilməz")
    String location;

    @NotEmpty(message = "Otaq sayı boş qoyula bilməz")
    String rooms;

    @NotEmpty(message = "Təmir vəziyyəti boş qoyula bilməz")
    String repair;

    @NotEmpty(message = "Çıxarış boş qoyula bilməz")
    String extract;

    @NotNull(message = "Minimum qiymət boş qoyula bilməz")
    @Min(value = 0 ,message = "Zəhmət olmasa keçərli qiymət daxil edin")
    Long minPrice;

    @NotNull(message = "Maximum qiymət boş qoyula bilməz")
    @Min(value = 0 ,message = "Zəhmət olmasa keçərli qiymət daxil edin")
    Long maxPrice;

    @NotNull(message = "Minimum sahə boş qoyula bilməz")
    @Min(value = 1 ,message = "Zəhmət olmasa keçərli dəyər daxil edin")
    Integer minArea;

    @NotNull(message = "Maximum sahə boş qoyula bilməz")
    @Min(value = 1 ,message = "Zəhmət olmasa keçərli dəyər daxil edin")
    Integer maxArea;

}
