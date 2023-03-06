package com.bina.az.binaazdata.controllers;

import com.bina.az.binaazdata.Jsoup.purchase.JsoupPurchaseLand;
import com.bina.az.binaazdata.dto.purchase.PurchaseLandDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("bina-az-data")
@RequiredArgsConstructor
public class PurchaseLandController {
    private final JsoupPurchaseLand land;

    @GetMapping("add-land-data")
    public PurchaseLandDto addNewLandData() throws IOException {
        return land.purchaseJsoupLandData();
    }

}
