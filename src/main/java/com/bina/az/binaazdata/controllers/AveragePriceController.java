package com.bina.az.binaazdata.controllers;

import com.bina.az.binaazdata.entity.PurchaseNewBuildingEntity;
import com.bina.az.binaazdata.service.PurchaseAveragePrice;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/average-price")
@RequiredArgsConstructor
public class AveragePriceController {

   private final PurchaseAveragePrice  averagePrice;




    @PostMapping ()
    public Long setAveragePrice(@ApiParam(value = "location", required = true)  @RequestParam String location,
                                @ApiParam(value = "optiona", required = false)  @RequestParam String rooms) throws IOException {


        return averagePrice.setAveragePrice(location,rooms);
   }


}
