package com.bina.az.binaazdata.controllers;

import com.bina.az.binaazdata.Jsoup.purchase.JsoupPurchaseNewBuilding;
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
public class PurchaseController {



    private final PurchaseNewBuildingService purchaseService;
    private final JsoupPurchaseNewBuilding jsoupPurchaseNewBuilding;




    @GetMapping("/addnewdata")
    public PurchaseNewBuildingDto addNewData() throws IOException {
       return jsoupPurchaseNewBuilding.purchaseJsoupNewBuildingData();


    }
}
