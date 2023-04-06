package com.bina.az.binaazdata.repository;

import com.bina.az.binaazdata.entity.PurchaseNewBuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

@Repository
public interface PurchaseNewBuildingRepository extends JpaRepository<PurchaseNewBuildingEntity,Integer> {

    ArrayList<PurchaseNewBuildingEntity> findAllByLocation(String loc);

   PurchaseNewBuildingEntity findByAnnouncementId(int id);

   ArrayList<PurchaseNewBuildingEntity> findAllByPriceBetween(Long firstPrice, Long lastPrice);

   ArrayList<PurchaseNewBuildingEntity> findAllByDateBetween(Date firstDate, Date lastDate);

   ArrayList<PurchaseNewBuildingEntity> findAllByAreaBetween(Integer minArea, Integer maxArea);

   ArrayList<PurchaseNewBuildingEntity>
   findAllByLocationAndRoomsAndExtractAndRepair(String location,String rooms,String extract,String repair);



}
