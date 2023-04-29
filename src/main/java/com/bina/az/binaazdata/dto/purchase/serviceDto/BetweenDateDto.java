package com.bina.az.binaazdata.dto.purchase.serviceDto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class BetweenDateDto {

    @NotNull(message = "Başlanöıc tarix boş qoyula bilməz")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    Date firstDate;

    @NotNull(message = "Son tarix boş qoyula bilməz")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    Date lastDate;
}
