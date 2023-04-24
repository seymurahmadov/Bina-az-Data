package com.bina.az.binaazdata.telegram.repository;

import com.bina.az.binaazdata.telegram.entity.TelegramEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramRepository extends JpaRepository<TelegramEntity,Long> {
   TelegramEntity findByChatId(Long chatId);
}
