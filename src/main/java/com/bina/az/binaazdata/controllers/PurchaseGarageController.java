package com.bina.az.binaazdata.controllers;

import com.bina.az.binaazdata.Jsoup.purchase.JsoupPurchaseGarage;
import com.bina.az.binaazdata.dto.purchase.PurchaseGarageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("bina-az-data")
@RequiredArgsConstructor
public class PurchaseGarageController {

    private final JsoupPurchaseGarage jsoupPurchaseGarage;

    @GetMapping("add-purchase-garage-data")
    public PurchaseGarageDto addGarageData() throws IOException {
        return jsoupPurchaseGarage.purchaseJsoupGarageData();
    }
}
