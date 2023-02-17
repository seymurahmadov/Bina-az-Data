package com.bina.az.binaazdata.repository;

import com.bina.az.binaazdata.entity.PurchaseLandEntity;
import com.bina.az.binaazdata.entity.PurchaseObjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseObjectRepository extends JpaRepository<PurchaseObjectEntity,Integer> {
}
