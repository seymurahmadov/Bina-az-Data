package com.bina.az.binaazdata.repository;

import com.bina.az.binaazdata.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long> {
    UserEntity findUsersEntityByEmail(String email);
}
