package com.bina.az.binaazdata.repository;

import com.bina.az.binaazdata.entity.PurchaseNewBuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PurchaseNewBuildingRepository extends JpaRepository<PurchaseNewBuildingEntity,Integer> {

    ArrayList<PurchaseNewBuildingEntity> findAllByLocation(String loc);

   PurchaseNewBuildingEntity findByAnnouncementId(int id);

//   ArrayList<PurchaseNewBuildingEntity> findAllByPrice(String firstPrice, String lastPrice);



}
