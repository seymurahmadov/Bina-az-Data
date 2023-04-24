package com.bina.az.binaazdata.dto.security;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class SignUpDto {

    @NotEmpty(message = "Mail boş qoyula bilməz")
    @Email(message ="Xətalı mail" )
    private String email;

    @NotEmpty(message = "Şifrə boş qoyula bilməz")
    @Size(min = 8,max = 20 ,message = "Şifrə uzunluğu minimum 8, maximum 20 olamlıdır")
    private String password;

    @NotEmpty(message ="Rol boş qoyula bilməz" )
    @Size(min = 8,max = 20 ,message = "Role uzunluğu minimum 8, maximum 20 olamlıdır")
    private String role;


    public SignUpDto(String email, String password) {
        this.email = email;
        this.password = password;

    }

}
