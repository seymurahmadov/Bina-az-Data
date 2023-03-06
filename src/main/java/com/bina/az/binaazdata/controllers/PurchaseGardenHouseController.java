package com.bina.az.binaazdata.controllers;

import com.bina.az.binaazdata.Jsoup.purchase.JsoupPurchaseGardenHouse;
import com.bina.az.binaazdata.dto.purchase.PurchaseGardenHouseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("bina-az-data")
@RequiredArgsConstructor
public class PurchaseGardenHouseController {
    private final JsoupPurchaseGardenHouse gardenHouse;

    @GetMapping("add-garden-house-data")
    public PurchaseGardenHouseDto addGardenHouseData() throws IOException {
        return gardenHouse.purchaseJsoupGardenHouseData();
    }
}
