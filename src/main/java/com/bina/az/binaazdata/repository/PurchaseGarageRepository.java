package com.bina.az.binaazdata.repository;

import com.bina.az.binaazdata.entity.PurchaseGarageEntity;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseGarageRepository extends JpaRepository<PurchaseGarageEntity,Integer> {
}
