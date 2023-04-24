package com.bina.az.binaazdata.telegram.telegramService;

import com.bina.az.binaazdata.entity.PurchaseNewBuildingEntity;
import com.bina.az.binaazdata.repository.PurchaseNewBuildingRepository;
import com.bina.az.binaazdata.telegram.dto.send.SendMessageResponseDTO;
import com.bina.az.binaazdata.telegram.dto.send.text.SendMessageDTO;
import com.bina.az.binaazdata.telegram.dto.update.TelegramResponseDTO;
import com.bina.az.binaazdata.telegram.dto.update.TelegramUpdateDTO;
import com.bina.az.binaazdata.telegram.entity.BetweenAreaEntity;
import com.bina.az.binaazdata.telegram.entity.BetweenDateEntity;
import com.bina.az.binaazdata.telegram.enums.BetweenDateChatStage;
import com.bina.az.binaazdata.telegram.repository.BetweenAreaRepository;
import com.bina.az.binaazdata.telegram.repository.BetweenDateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class BotSchedulerBetDate {
    @Value("${telegram.api.base-url}")
    String api;

    @Value("${telegram.api.token}")
    String token;

    private   Long offset = null;

    private final BetweenDateRepository betweenDateRepository;
    private final PurchaseNewBuildingRepository newBuildingRepository;

    @Scheduled(fixedRate = 3000)
    public void getUpdatesByDate() throws IOException, ParseException {
        String url = api + "/bot" + token + "/getUpdates";

        if (offset!=null){
            url = url + "?offset=" + offset;
        }

        RestTemplate restTemplate = new RestTemplate();
        TelegramResponseDTO telegramResponseDTO = restTemplate.getForObject(url,TelegramResponseDTO.class);

        if (telegramResponseDTO.getResult().size()>0){
            if (telegramResponseDTO.getResult()!=null){
                TelegramUpdateDTO telegramUpdateDTO = telegramResponseDTO.getResult().get(0);
                offset=telegramUpdateDTO.getUpdateId()+1;

                String text = telegramUpdateDTO.getMessageDTO().getText();
                Long id = telegramUpdateDTO.getMessageDTO().getChat().getId();

                BetweenDateEntity byChatId = betweenDateRepository.findByChatId(id);


                if (byChatId==null){
                    BetweenDateEntity entity = BetweenDateEntity.builder()
                            .chatId(id)
                            .build();

                    betweenDateRepository.save(entity);

                }

                if (text.equals("/start")){
                    sendMessage("Bina.az botuna xoş gəlmisiniz. Zəhmət olmasa xidməti seçin", id);
                    sendMessage("/betweenprices",id);
                    sendMessage("/betweenarea",id);
                    sendMessage("/betweendates",id);
                    sendMessage("/averageprice",id);
                    sendMessage("/generalsort",id);
                }

                if (text.equals("/betweendates")) {
                    sendMessage("Müəyyən tarix aralığında evlərin tapılması xidməti", id);
                    sendMessage("Zəhmət olmasa başlanğıc tarixi daxil edin:\nMisal olaraq: 2023-05-20", id);

                    byChatId.setChatStage(BetweenDateChatStage.MIN_DATE.name());
                    betweenDateRepository.save(byChatId);
                }else if (byChatId.getChatStage().equals(BetweenDateChatStage.MIN_DATE.name())){
                    byChatId.setMinDate(text);
                    betweenDateRepository.save(byChatId);

                    byChatId.setChatStage(BetweenDateChatStage.MAX_DATE.name());
                    betweenDateRepository.save(byChatId);

                    sendMessage("Zəhmət olmasa son tarixi daxil edin:\nMisal olaraq: 2023-05-20", id);


                }else if (byChatId.getChatStage().equals(BetweenDateChatStage.MAX_DATE.name())){
                    byChatId.setMaxDate(text);
                    betweenDateRepository.save(byChatId);

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date minDate = dateFormat.parse(byChatId.getMinDate());
                    Date maxDate = dateFormat.parse(byChatId.getMaxDate());


                    ArrayList<PurchaseNewBuildingEntity> allByDateBetween =
                            newBuildingRepository.findAllByDateBetween(minDate,maxDate);

                    for (PurchaseNewBuildingEntity item : allByDateBetween){
                        sendMessage("https://bina.az/items/" + item.getAnnouncementId(),id);
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
