package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

//Entity機能「DBデータをJavaで扱う」付与
@Entity
//Getter,Setterを自動作成
//Entityクラス内にブラウザで入力したデータの保存を許可する「set変数名();メソッド」を自動的に作成する
@Data
//DBのadminsテーブルを扱う
@Table(name = "admins")
public class Admin {
	
	//idカラム＝主キー
	@Id
	//JavaからDBへ新しくデータを保存する際に自動で識別番号を振り分ける
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	//カプセル化。他のクラスからのデータ操作を防ぐため
	private Long id;
	
	private String lastName;
	
	private String firstName;
	
	private String email;
	
	private String password;

}
