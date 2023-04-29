package com.bina.az.binaazdata.dto.purchase.serviceDto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BetweenAreaDto {


    @NotNull(message = "Minimum sahə boş qoyula bilməz")
    @Min(value = 1 ,message = "Zəhmət olmasa keçərli dəyər daxil edin")
    Integer minArea;


    @NotNull(message = "Maximum sahə boş qoyula bilməz")
    @Min(value = 1 ,message = "Zəhmət olmasa keçərli dəyər daxil edin")
    Integer maxArea;
}
