package com.bina.az.binaazdata.controllers;

import com.bina.az.binaazdata.dto.PurchaseDto;
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
    public PurchaseEntity addNewData(@RequestBody PurchaseDto dto){
       return purchaseService.addNewPurchaseData(dto);


    }
}
