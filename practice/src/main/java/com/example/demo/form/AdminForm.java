package com.example.demo.form;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
//役割：バリデーション（入力値チェック）をするためのクラス
public class AdminForm implements Serializable{
	
	//バリデーション：空文字禁止
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String password;
}