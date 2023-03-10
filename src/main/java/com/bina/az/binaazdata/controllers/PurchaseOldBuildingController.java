package com.bina.az.binaazdata.controllers;


import com.bina.az.binaazdata.JsoupService.purchase.JsoupPurchaseOldBuilding;
import com.bina.az.binaazdata.dto.purchase.PurchaseOldBuildingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("bina-az-data")
@RequiredArgsConstructor
public class PurchaseOldBuildingController {

    private final JsoupPurchaseOldBuilding oldBuilding;

    @GetMapping("add-purchase-old-building-data")
    public PurchaseOldBuildingDto addOldBuildingData() throws IOException {
        return oldBuilding.purchaseJsoupOldBuildingData();
    }


}
