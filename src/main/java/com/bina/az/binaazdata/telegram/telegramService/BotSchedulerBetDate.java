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

    private final TelegramRepository telegramRepository;
    private final PurchaseNewBuildingRepository newBuildingRepository;

    public void getUpdatesByDate(String text,Long id,TelegramEntity byChatId) throws IOException, ParseException {


        if (text.equals("/betweendates")) {
            sendMessage("Müəyyən tarix aralığında evlərin tapılması xidməti", id);
            sendMessage("Zəhmət olmasa başlanğıc tarixi daxil edin:\nMisal olaraq: 2023-05-20", id);

            byChatId.setChatStage(TelegramEnum.MIN_DATE.name());
            telegramRepository.save(byChatId);
        } else if (byChatId.getChatStage().equals(TelegramEnum.MIN_DATE.name())) {
            byChatId.setMinDate(text);
            telegramRepository.save(byChatId);

            byChatId.setChatStage(TelegramEnum.MAX_DATE.name());
            telegramRepository.save(byChatId);

            sendMessage("Zəhmət olmasa son tarixi daxil edin:\nMisal olaraq: 2023-05-20", id);


        } else if (byChatId.getChatStage().equals(TelegramEnum.MAX_DATE.name())) {
            byChatId.setMaxDate(text);
            telegramRepository.save(byChatId);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date minDate = dateFormat.parse(byChatId.getMinDate());
            Date maxDate = dateFormat.parse(byChatId.getMaxDate());


            ArrayList<PurchaseNewBuildingEntity> allByDateBetween =
                    newBuildingRepository.findAllByDateBetween(minDate, maxDate);

            if (allByDateBetween.size()>0) {
                for (PurchaseNewBuildingEntity item : allByDateBetween) {
                    sendMessage("https://bina.az/items/" + item.getAnnouncementId(), id);
                }
            } else if (allByDateBetween.size()==0) {
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
