package com.bina.az.binaazdata.telegram.dto.update;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class TelegramUpdateDTO {

    @JsonProperty("update_id")
    private Long updateId;

    @JsonProperty("message")
    private MessageDTO messageDTO;

}
