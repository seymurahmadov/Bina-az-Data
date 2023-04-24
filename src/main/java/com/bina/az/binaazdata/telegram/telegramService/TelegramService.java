package com.bina.az.binaazdata.telegram.telegramService;

import com.bina.az.binaazdata.telegram.dto.send.SendMessageResponseDTO;
import com.bina.az.binaazdata.telegram.dto.send.text.SendMessageDTO;
import com.bina.az.binaazdata.telegram.dto.update.TelegramResponseDTO;
import com.bina.az.binaazdata.telegram.dto.update.TelegramUpdateDTO;
import com.bina.az.binaazdata.telegram.entity.TelegramEntity;
import com.bina.az.binaazdata.telegram.enums.TelegramEnum;
import com.bina.az.binaazdata.telegram.repository.TelegramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.ParseException;

@Service
@RequiredArgsConstructor
public class TelegramService {
    private final BotSchedulerBetArea betweenArea;
    private final BotSchedulerBetDate betweenDate;
    private final BotSchedulerBetPrice betweenPrice;


    @Value("${telegram.api.base-url}")
    String api;

    @Value("${telegram.api.token}")
    String token;

    private Long offset = null;

    private final TelegramRepository telegramRepository;

    @Scheduled(fixedRate = 3000)
    public void mainService() throws IOException, ParseException {
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

                String text = telegramUpdateDTO.getMessageDTO().getText();
                Long id = telegramUpdateDTO.getMessageDTO().getChat().getId();



                //ChatStage Control
                TelegramEntity byChatId = telegramRepository.findByChatId(id);
//              repository.save(byChatId);



                if (byChatId == null) {
                    TelegramEntity entity = TelegramEntity.builder()
                            .chatId(id)
                            .chatStage(TelegramEnum.START.name())
                            .build();
                    telegramRepository.save(entity);
                }


                if (text.equals("/start")) {
                    byChatId.setChatStage(TelegramEnum.START.name());
                    sendMessage("Bina.az botuna xoş gəlmisiniz. Zəhmət olmasa xidməti seçin", id);
                    sendMessage("/betweenprices", id);
                    sendMessage("/betweenarea", id);
                    sendMessage("/betweendates", id);
                    sendMessage("/averageprice", id);
                    sendMessage("/generalsort", id);
                }


                betweenPrice.getUpdatesByPrice(text,id,byChatId);
                betweenArea.getUpdatesByArea(text,id,byChatId);
                betweenDate.getUpdatesByDate(text,id,byChatId);

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
