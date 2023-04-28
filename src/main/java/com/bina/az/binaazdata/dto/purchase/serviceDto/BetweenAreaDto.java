package com.bina.az.binaazdata.dto.purchase.serviceDto;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class BetweenAreaDto {

    @NotNull
    @Size(min = 1 ,message = "Zəhmət olmasa müsbət qiymət daxil edin")
    Integer minArea;

    @NotNull
    @Size(min = 1 ,message = "Zəhmət olmasa müsbət qiymət daxil edin")
    Integer maxArea;
}
