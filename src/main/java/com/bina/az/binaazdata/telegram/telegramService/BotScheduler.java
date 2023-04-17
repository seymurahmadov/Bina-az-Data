package com.bina.az.binaazdata.telegram.telegramService;

import com.bina.az.binaazdata.telegram.entity.BetweenPriceEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BotScheduler {

    @Value("${telegram.api.base-url}")
    String api;

    @Value("${telegram.api.token}")
    String token;

    private Long offset = null;


    @Scheduled(fixedRate = 3000)
    public void getUpdates(){
    String url = api + "/bot" + token + "/getUpdates";

    if (offset!=null){
        url= url + "?offset=" + offset;
        }

    RestTemplate restTemplate = new RestTemplate();
        BetweenPriceEntity forObject =restTemplate.getForObject(url,BetweenPriceEntity.class);









        }



}
