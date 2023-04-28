package com.bina.az.binaazdata.telegram.telegramService;

import com.bina.az.binaazdata.entity.PurchaseNewBuildingEntity;
import com.bina.az.binaazdata.repository.PurchaseNewBuildingRepository;
import com.bina.az.binaazdata.telegram.dto.send.SendMessageResponseDTO;
import com.bina.az.binaazdata.telegram.dto.send.text.SendMessageDTO;
import com.bina.az.binaazdata.telegram.entity.TelegramEntity;
import com.bina.az.binaazdata.telegram.enums.TelegramEnum;
import com.bina.az.binaazdata.telegram.repository.TelegramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BotSchedulerBetPrice {
    @Value("${telegram.api.base-url}")
    String api;

    @Value("${telegram.api.token}")
    String token;

    private final TelegramRepository telegramRepository;

    private final PurchaseNewBuildingRepository newBuildingRepository;


    public void getUpdatesByPrice(String text, Long id, TelegramEntity byChatId ) throws IOException {


        if (text.equals("/betweenprices")) {
            sendMessage("Müəyyən qiymət aralıqda evlərin tapılması xidməti", id);
            sendMessage("Zəhmət olmasa minimum qiyməti daxil edin:", id);

            //ChatStageSave
            byChatId.setChatStage(TelegramEnum.MIN_PRICE.name());
            telegramRepository.save(byChatId);

        }

        //ChatStageControl
        else if (byChatId.getChatStage().equals(TelegramEnum.MIN_PRICE.name())) {

            //SetPrice
            byChatId.setMinPrice(text);
            telegramRepository.save(byChatId);

            byChatId.setChatStage(TelegramEnum.MAX_PRICE.name());
            telegramRepository.save(byChatId);

            sendMessage("Zəhmət olmasa maximum qiyməti daxil edin:", id);

        } else if (byChatId.getChatStage().equals(TelegramEnum.MAX_PRICE.name())) {

            byChatId.setMaxPrice(text);
            telegramRepository.save(byChatId);

            Long minPriceLong= Long.valueOf(0);
            Long maxPriceLong= Long.valueOf(0);

            try {
                minPriceLong = Long.parseLong(byChatId.getMinPrice());
                maxPriceLong = Long.parseLong(byChatId.getMaxPrice());
            }catch (Exception e){
                e.getMessage();
            }


            ArrayList<PurchaseNewBuildingEntity> allByAnnouncementIdBetweenPrice =
                    newBuildingRepository.findAllByPriceBetween(minPriceLong, maxPriceLong);

            if (allByAnnouncementIdBetweenPrice.size() > 0) {
                for (PurchaseNewBuildingEntity a : allByAnnouncementIdBetweenPrice) {
                    sendMessage("https://bina.az/items/" + a.getAnnouncementId(), id);
                }
            } else if (allByAnnouncementIdBetweenPrice.size() == 0) {
                sendMessage("O aralıqda evlər tapilmadı", id);
            }

        }


    }







    public void sendMessage(String text, Long id) throws IOException {
        String url1 = api + "/bot" + token + "/sendMessage";


        SendMessageDTO dto = SendMessageDTO.builder()
                .chatId(id)
                .text(text)
                .build();


        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url1, dto, SendMessageResponseDTO.class);

    }
}