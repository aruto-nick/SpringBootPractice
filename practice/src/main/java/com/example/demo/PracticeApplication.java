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
		//①下記URLのアクセス権限について宣言
		http
			.authorizeHttpRequests(auth -> auth
				//ログイン画面・新規登録画面は「全員アクセス許可」
				.requestMatchers("/admin/signin", "/admin/signup").permitAll()
				//そのほかのURLは「ログイン必須」
				.anyRequest().authenticated()
			)
			
			//②ログイン機能
			.formLogin(login ->  login
					//ログイン画面のURL
					.loginPage("/admin/signin")
					//ログイン成功後に移動するURL「お問い合わせ一覧画面」
					.defaultSuccessUrl("/admin/contact", true)
					//ログイン画面は「全員アクセス許可」
					.permitAll()
			)
			
			//③ログアウト機能
			.logout(logout -> logout
					//Spring Securityに"/admin/logout"というログアウト用の窓口作成をしじ
					.logoutUrl("/admin/logout")
					//ログアウト成功後に移動するURL
					//※「？以下」を付けることでログアウトによってログイン画面に移動と判別できる
					.logoutSuccessUrl("/admin/signin?logout")
					
					//全てのサイトからログアウトできる
					.permitAll()
			
			)
			
			
			// 登録機能（POST送信）でのエラーを防ぐためにCSRF（セキュリティ機能）を一時無効化
			.csrf(csrf -> csrf.disable()); 
		return http.build();
	}

}
