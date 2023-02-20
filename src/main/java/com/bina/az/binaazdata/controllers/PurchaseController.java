package com.bina.az.binaazdata.controllers;

import com.bina.az.binaazdata.dto.purchase.PurchaseNewBuildingDto;
import com.bina.az.binaazdata.entity.PurchaseNewBuildingEntity;
import com.bina.az.binaazdata.service.PurchaseNewBuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/binaazdata")
@RequiredArgsConstructor
public class PurchaseController {



    private final PurchaseNewBuildingService purchaseService;




    @GetMapping("/addnewdata")
    public PurchaseNewBuildingEntity addNewData() throws IOException {
       return purchaseService.saveNewBuildingJsoupData();


    }
}
