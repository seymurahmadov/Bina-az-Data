package com.bina.az.binaazdata.telegram.dto.send.text;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
//import com.azal.bot.dto.telegram.send.ReplyKeyboard;

@Data
@Builder
public class SendMessageDTO {

    @JsonProperty("chat_id")
    private Long chatId;

    @JsonProperty("text")
    private String text;

//    @JsonProperty("reply_markup")
//    private ReplyKeyboard replyKeyboard;

}
