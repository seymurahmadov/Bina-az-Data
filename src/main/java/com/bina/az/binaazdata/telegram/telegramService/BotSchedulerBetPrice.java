package com.bina.az.binaazdata.telegram.telegramService;

import com.bina.az.binaazdata.entity.PurchaseNewBuildingEntity;
import com.bina.az.binaazdata.repository.PurchaseNewBuildingRepository;
import com.bina.az.binaazdata.telegram.dto.send.SendMessageResponseDTO;
import com.bina.az.binaazdata.telegram.dto.send.text.SendMessageDTO;
import com.bina.az.binaazdata.telegram.dto.update.TelegramResponseDTO;
import com.bina.az.binaazdata.telegram.dto.update.TelegramUpdateDTO;
import com.bina.az.binaazdata.telegram.entity.BetweenPriceEntity;
import com.bina.az.binaazdata.telegram.enums.BetweenPriceChatStage;
import com.bina.az.binaazdata.telegram.repository.BetweenPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
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

    private Long offset = null;

    private final BetweenPriceRepository repository;

    private final PurchaseNewBuildingRepository newBuildingRepository;


    @Scheduled(fixedRate = 3000)
    public void getUpdates() throws IOException {
    String url = api + "/bot" + token + "/getUpdates";

    if (offset!=null){
        url= url + "?offset=" + offset;
        }

        RestTemplate restTemplate = new RestTemplate();

        TelegramResponseDTO forObject = restTemplate.getForObject(url,TelegramResponseDTO.class);

        if (forObject.getResult().size()>0){
            if (forObject.getResult().get(0) != null) {
                TelegramUpdateDTO telegramUpdateDTO = forObject.getResult().get(0);
                offset = telegramUpdateDTO.getUpdateId() + 1;

                String text=telegramUpdateDTO.getMessageDTO().getText();
                Long id = telegramUpdateDTO.getMessageDTO().getChat().getId();

                //ChatStage Control
                BetweenPriceEntity byChatId = repository.findByChatId(id);
//                repository.save(byChatId);

                if (byChatId == null) {
                    BetweenPriceEntity entity = BetweenPriceEntity.builder()
                            .chatId(id)
                            .build();
                    repository.save(entity);
                }


                if (text.equals("/start")) {
                    sendMessage("Bina.az botuna xoş gəlmisiniz. Zəhmət olmasa xidməti seçin", id);
                    sendMessage("/betweenprices",id);
                    sendMessage("/betweenarea",id);
                    sendMessage("/betweendates",id);
                    sendMessage("/averageprice",id);
                    sendMessage("/generalsort",id);
                }

                if (text.equals("/betweenprices")) {
                    sendMessage("Müəyyən qiymət aralıqda evlərin tapılması xidməti", id);
                    sendMessage("Zəhmət olmasa minimum qiyməti daxil edin:", id);

                    //ChatStageSave
                    byChatId.setChatStage(BetweenPriceChatStage.MIN_PRICE.name());
                    repository.save(byChatId);

                }
                //ChatStageControl
                else if (byChatId.getChatStage().equals(BetweenPriceChatStage.MIN_PRICE.name())){

                    //SetPrice
                    byChatId.setMinPrice(text);
                    repository.save(byChatId);

                    byChatId.setChatStage(BetweenPriceChatStage.MAX_PRICE.name());
                    repository.save(byChatId);

                    sendMessage("Zəhmət olmasa maximum qiyməti daxil edin:",id);

                }else if (byChatId.getChatStage().equals(BetweenPriceChatStage.MAX_PRICE.name())){

                    byChatId.setMaxPrice(text);
                    repository.save(byChatId);

                    Long minPriceLong = Long.parseLong(byChatId.getMinPrice());
                    Long maxPriceLong = Long.parseLong(byChatId.getMaxPrice());
                    ArrayList<PurchaseNewBuildingEntity> allByAnnouncementIdBetweenPrice =
                            newBuildingRepository.findAllByPriceBetween(minPriceLong,maxPriceLong);

                    if (allByAnnouncementIdBetweenPrice.size()>0) {
                        for (PurchaseNewBuildingEntity a : allByAnnouncementIdBetweenPrice) {
                            sendMessage("https://bina.az/items/" + a.getAnnouncementId(), id);
                        }
                    }
                  else if (allByAnnouncementIdBetweenPrice.size()==0){
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
