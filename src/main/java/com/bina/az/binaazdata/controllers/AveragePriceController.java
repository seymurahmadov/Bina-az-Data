package com.bina.az.binaazdata.controllers;

import com.bina.az.binaazdata.entity.PurchaseNewBuildingEntity;
import com.bina.az.binaazdata.service.PurchaseAveragePrice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/average-price")
@RequiredArgsConstructor
public class AveragePriceController {

   private final PurchaseAveragePrice  averagePrice;

   @GetMapping()
    public Double setAveragePrice( ) throws IOException {
        return averagePrice.setAveragePrice();
   }


}
