package com.bina.az.binaazdata.repository;

import com.bina.az.binaazdata.dto.purchase.PurchaseNewBuildingDto;
import com.bina.az.binaazdata.entity.PurchaseNewBuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseNewBuildingRepository extends JpaRepository<PurchaseNewBuildingEntity,Integer> {



}
