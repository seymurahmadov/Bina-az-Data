package com.bina.az.binaazdata.controllers;


import com.bina.az.binaazdata.JsoupService.purchase.JsoupPurchaseObject;
import com.bina.az.binaazdata.dto.purchase.PurchaseObjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("bina-az-data")
@RequiredArgsConstructor
public class PurchaseObjectController {
    private final JsoupPurchaseObject jsoupPurchaseObject;

    @GetMapping("add-object-data")
    public PurchaseObjectDto addObjectData() throws IOException {
        return jsoupPurchaseObject.purchaseJsoupObjectData();
    }
}
