package com.bina.az.binaazdata.controllers;

import com.bina.az.binaazdata.JsoupService.purchase.JsoupPurchaseNewBuilding;
import com.bina.az.binaazdata.dto.purchase.PurchaseNewBuildingDto;
import com.bina.az.binaazdata.service.PurchaseNewBuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/binaazdata")
@RequiredArgsConstructor
public class PurchaseNewBuildingController {



    private final PurchaseNewBuildingService purchaseService;
    private final JsoupPurchaseNewBuilding jsoupPurchaseNewBuilding;




    @GetMapping("/add-purchase-new-building-data")
    public PurchaseNewBuildingDto addNewBuildingData() throws IOException {
       return jsoupPurchaseNewBuilding.purchaseJsoupNewBuildingData();


    }
}
