package com.bina.az.binaazdata.controllers;

import com.bina.az.binaazdata.dto.purchase.AveragePriceDto;
import com.bina.az.binaazdata.dto.purchase.AveragePriceDto2;
import com.bina.az.binaazdata.entity.PurchaseNewBuildingEntity;
import com.bina.az.binaazdata.service.PurchaseAveragePrice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/average-price")
@RequiredArgsConstructor
public class AveragePriceController {

   private final PurchaseAveragePrice  averagePrice;




    @PostMapping ()
    public Long setAveragePrice(@RequestBody AveragePriceDto dto) throws IOException, ParseException {


        return averagePrice.setAveragePrice(dto);
   }

    @PostMapping ()
    public void setBetweenPrice(@RequestBody AveragePriceDto2 dto2) throws IOException, ParseException {


        averagePrice.findBetweenPrice(dto2);
    }


}
