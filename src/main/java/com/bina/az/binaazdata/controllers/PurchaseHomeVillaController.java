package com.bina.az.binaazdata.controllers;

import com.bina.az.binaazdata.JsoupService.purchase.JsoupPurchaseHomeVilla;
import com.bina.az.binaazdata.dto.purchase.PurchaseHomeVillaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("bina-az-data")
@RequiredArgsConstructor
public class PurchaseHomeVillaController {

    private final JsoupPurchaseHomeVilla jsoupPurchaseHomeVilla;

    @GetMapping("add-purchase-home-villa-data")
    public PurchaseHomeVillaDto addPurchaseHomeVillaData() throws IOException {
        return jsoupPurchaseHomeVilla.purchaseJsoupHomeVillaData();
    }
}
