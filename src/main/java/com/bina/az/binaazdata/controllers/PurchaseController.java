package com.bina.az.binaazdata.controllers;

import com.bina.az.binaazdata.dto.purchase.PurchaseNewBuildingDto;
import com.bina.az.binaazdata.entity.PurchaseNewBuildingEntity;
import com.bina.az.binaazdata.service.PurchaseNewBuildingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/binaazdata")
public class PurchaseController {



    private final PurchaseNewBuildingService purchaseService;

    public PurchaseController(PurchaseNewBuildingService purchaseService) {
        this.purchaseService = purchaseService;
    }


    @PostMapping("/addnewdata")
    public PurchaseNewBuildingEntity addNewData(List<PurchaseNewBuildingDto> dtoList){
       return purchaseService.saveNewBuildingJsoupData(dtoList);


    }
}
