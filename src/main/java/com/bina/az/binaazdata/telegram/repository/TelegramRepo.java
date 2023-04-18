package com.bina.az.binaazdata.telegram.repository;

import com.bina.az.binaazdata.telegram.entity.BetweenPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramRepo extends JpaRepository<BetweenPriceEntity,Long> {
    BetweenPriceEntity findByChatId(Long chatId);
}
