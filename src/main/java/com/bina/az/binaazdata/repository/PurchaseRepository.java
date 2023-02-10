package com.bina.az.binaazdata.repository;

import com.bina.az.binaazdata.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseEntity,Integer> {

}
