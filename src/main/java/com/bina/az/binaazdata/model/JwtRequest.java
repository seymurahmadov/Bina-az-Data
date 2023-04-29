package com.bina.az.binaazdata.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	@NotEmpty(message = "Mail boş qoyula bilməz")
	@Email(message ="Xətalı mail" )
	private String email;

	@NotEmpty(message = "Şifrə boş qoyula bilməz")
	@Size(min = 8,max = 20 ,message = "Şifrə uzunluğu minimum 8, maximum 20 olamlıdır")
	private String password;

	@NotEmpty(message ="Rol boş qoyula bilməz" )
	@Size(min = 2,max = 20 ,message = "Role uzunluğu minimum 2, maximum 20 olamlıdır")
	private String role;

	
	//need default constructor for JSON Parsing
	public JwtRequest()
	{
		
	}

	public JwtRequest(String email, String password) {
		this.setEmail(email);
		this.setPassword(password);
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}