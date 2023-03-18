package com.bina.az.binaazdata.controllers;

import com.bina.az.binaazdata.dto.purchase.AveragePriceDto;
import com.bina.az.binaazdata.service.PurchaseAveragePrice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/average-price")
@RequiredArgsConstructor
public class AveragePriceController {

   private final PurchaseAveragePrice  averagePrice;




    @PostMapping ()
    public Long setAveragePrice(@RequestBody AveragePriceDto dto) throws IOException {


        return averagePrice.setAveragePrice(dto);
   }


}
