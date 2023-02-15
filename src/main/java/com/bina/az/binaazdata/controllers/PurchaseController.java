package com.bina.az.binaazdata.controllers;

import com.bina.az.binaazdata.dto.purchase.PurchaseNewBuildingDto;
import com.bina.az.binaazdata.entity.PurchaseEntity;
import com.bina.az.binaazdata.service.PurchaseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/binaazdata")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/addnewdata")
    public PurchaseEntity addNewData(@RequestBody PurchaseNewBuildingDto dto){
       return purchaseService.addNewPurchaseData(dto);


    }
}
