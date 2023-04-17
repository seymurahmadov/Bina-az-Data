package com.bina.az.binaazdata.entity;

import  com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
//    @Size(min = 3, max = 20)
    private String email;

    @NotNull
//    @Size(min = 3,max = 20)
    private String password;

    @NotNull
    private String role;

}
