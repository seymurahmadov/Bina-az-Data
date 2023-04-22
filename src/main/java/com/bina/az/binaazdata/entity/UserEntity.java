package com.bina.az.binaazdata.entity;

import  com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
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
//    @Email(message ="Email length can be minimum 8 and maximum 20" )
//    @Size(min = 8, max = 20 ,message = "Email length can be minimum 8 and maximum 20")
    private String email;

    @NotNull
//    @Size(min = 8,max = 20 ,message = "Password length can be minimum 8 and maximum 20")
    private String password;

    @NotNull
//    @Size(min = 8,max = 20 ,message = "Role length can be minimum 8 and maximum 20")
      private String role;

}
