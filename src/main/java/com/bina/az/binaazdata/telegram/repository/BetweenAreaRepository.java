package com.bina.az.binaazdata.telegram.repository;


import com.bina.az.binaazdata.telegram.entity.BetweenAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetweenAreaRepository extends JpaRepository<BetweenAreaEntity,Long> {
BetweenAreaEntity findByChatId(Long chatId);
}
