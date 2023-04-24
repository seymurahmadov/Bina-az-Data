package com.bina.az.binaazdata.telegram.telegramService;

import com.bina.az.binaazdata.entity.PurchaseNewBuildingEntity;
import com.bina.az.binaazdata.repository.PurchaseNewBuildingRepository;
import com.bina.az.binaazdata.telegram.dto.send.SendMessageResponseDTO;
import com.bina.az.binaazdata.telegram.dto.send.text.SendMessageDTO;
import com.bina.az.binaazdata.telegram.dto.update.TelegramResponseDTO;
import com.bina.az.binaazdata.telegram.dto.update.TelegramUpdateDTO;
import com.bina.az.binaazdata.telegram.entity.BetweenAreaEntity;
import com.bina.az.binaazdata.telegram.enums.BetweenAreaChatStage;
import com.bina.az.binaazdata.telegram.repository.BetweenAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BotSchedulerBetArea {
    @Value("${telegram.api.base-url}")
    String api;

    @Value("${telegram.api.token}")
    String token;

    private   Long offset = null;

    private final BetweenAreaRepository betweenAreaRepository;
    private final PurchaseNewBuildingRepository newBuildingRepository;

    @Scheduled(fixedRate = 3000)
    public void getUpdatesByArea() throws IOException {
        String url= api + "/bot" +token + "/getUpdates";

        if (offset!=null){
            url = url +"?offset=" + offset;
        }

        RestTemplate restTemplate= new RestTemplate();
        TelegramResponseDTO forObject = restTemplate.getForObject(url,TelegramResponseDTO.class);
        if (forObject.getResult().size()>0){
            if (forObject.getResult()!=null){
                TelegramUpdateDTO telegramUpdateDTO = forObject.getResult().get(0);
                offset=telegramUpdateDTO.getUpdateId()+1;

                String text = telegramUpdateDTO.getMessageDTO().getText();
                Long id = telegramUpdateDTO.getMessageDTO().getChat().getId();

                BetweenAreaEntity byChatId = betweenAreaRepository.findByChatId(id);


                if (byChatId==null){
                   BetweenAreaEntity entity = BetweenAreaEntity.builder()
                            .chatId(id)
                            .build();
                    betweenAreaRepository.save(entity);
                }

               if (text.equals("/start")){
                   sendMessage("Bina.az botuna xoş gəlmisiniz. Zəhmət olmasa xidməti seçin", id);
                   sendMessage("/betweenprices",id);
                   sendMessage("/betweenarea",id);
                   sendMessage("/betweendates",id);
                   sendMessage("/averageprice",id);
                   sendMessage("/generalsort",id);
               }
                if (text.equals("/betweenarea")){
                   sendMessage("Müəyyən kvadrat aralıqda evlərin tapılması xidməti",id);
                   sendMessage("Zəhmət olmasa minimum m2 daxil edin:",id);

                   byChatId.setChatStage(BetweenAreaChatStage.MIN_AREA.name());
                   betweenAreaRepository.save(byChatId);

               } else if (byChatId.getChatStage().equals(BetweenAreaChatStage.MIN_AREA.name())) {
                   byChatId.setMinArea(text);
                   betweenAreaRepository.save(byChatId);

                   byChatId.setChatStage(BetweenAreaChatStage.MAX_AREA.name());
                   betweenAreaRepository.save(byChatId);
                   sendMessage("Zəhmət olmasa maximum m2 daxil edin:",id);
               } else if (byChatId.getChatStage().equals(BetweenAreaChatStage.MAX_AREA.name())) {
                   byChatId.setMaxArea(text);
                   betweenAreaRepository.save(byChatId);

                   Integer minArea = Integer.valueOf(byChatId.getMinArea());
                   Integer maxArea = Integer.valueOf(byChatId.getMaxArea());

                   ArrayList<PurchaseNewBuildingEntity> allByAreaBetween =
                           newBuildingRepository.findAllByAreaBetween(minArea, maxArea);


                   if (allByAreaBetween.size()>0) {
                       for (PurchaseNewBuildingEntity item : allByAreaBetween) {
                           sendMessage("https://bina.az/items/" + item.getAnnouncementId(), id);
                       }
                   }

                 else if (allByAreaBetween.size()==0 || allByAreaBetween==null){
                       sendMessage("O aralıqda evlər tapilmadı",id);
                   }


               }

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
