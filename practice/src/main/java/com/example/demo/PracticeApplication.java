package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class PracticeApplication{ 

	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);
	}
	
	//下記メソッドで作成したツールを他クラスで使用できる：暗号化ツールの呼び出し@Autowiredができる
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	//ブラウザからのリクエストを通していいかチェックする
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//下記URLのアクセス権限について宣言
		http
			.authorizeHttpRequests(auth -> auth
				// すべてのURLへのアクセスを許可＝ログイン画面の非表示
				.anyRequest().permitAll() 
			)
			// 登録機能（POST送信）でのエラーを防ぐためにCSRF（セキュリティ機能）を一時無効化
			.csrf(csrf -> csrf.disable()); 
		return http.build();
	}

}
