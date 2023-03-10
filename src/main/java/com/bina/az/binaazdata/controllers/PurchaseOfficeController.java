package com.bina.az.binaazdata.controllers;

import com.bina.az.binaazdata.JsoupService.purchase.JsoupPurchaseOffice;
import com.bina.az.binaazdata.dto.purchase.PurchaseOfficeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("bina-az-data")
@RequiredArgsConstructor
public class PurchaseOfficeController {
    private final JsoupPurchaseOffice jsoupPurchaseOffice;

    @GetMapping("add-office-data")
    public PurchaseOfficeDto addOfficeData() throws IOException {
        return jsoupPurchaseOffice.purchaseJsoupOfficeData();
    }
}
