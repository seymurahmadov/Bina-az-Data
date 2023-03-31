package com.bina.az.binaazdata.controllers;


import com.bina.az.binaazdata.service.AService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Test")
@RequiredArgsConstructor
public class AController {
private final     AService aService ;

@GetMapping
    public void testC(){
     aService.test();
}
}
