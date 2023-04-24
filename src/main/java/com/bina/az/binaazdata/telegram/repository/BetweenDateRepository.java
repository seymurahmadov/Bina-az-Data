package com.bina.az.binaazdata.telegram.repository;

import com.bina.az.binaazdata.telegram.entity.BetweenDateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BetweenDateRepository extends JpaRepository<BetweenDateEntity,Long> {
    BetweenDateEntity findByChatId(Long id);
}
