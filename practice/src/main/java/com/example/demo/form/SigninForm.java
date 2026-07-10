package com.example.demo.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SigninForm {

	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String password;
}
