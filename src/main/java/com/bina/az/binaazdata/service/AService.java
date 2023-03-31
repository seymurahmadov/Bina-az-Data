package com.bina.az.binaazdata.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AService {

//    @Scheduled(fixedRate = 10000)
    public void test(){
        System.out.println(LocalDateTime.now());
    }

}
