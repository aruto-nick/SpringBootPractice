package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
//暗号化ツールの導入
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.form.SignupForm;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	//AfminRepositoryを利用できるようにするため
	@Autowired
	private AdminRepository adminRepository;
	
	//暗号化ツールのリモコン
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//AdminServiceインターフェースにて宣言したメソッドの内容記述
	@Override
	public void register(SignupForm signupForm) {
		
		//画面から届いたパスワードを取り出す
		String rawPassword = signupForm.getPassword();
		
		//暗号化ツールを活用して取り出したパスワードをハッシュ値（解読不能な文字）に変える
		String encodedPassword = passwordEncoder.encode(rawPassword);
		
		Admin admin = new Admin();
		
		//setterによってカプセル化されたpassword変数の変更ができる
		admin.setPassword(encodedPassword);
		
		//Formの中からLastNameを取り出して、Entity(admin)にセットしなさい！
		admin.setLastName(signupForm.getLastName());
		admin.setFirstName(signupForm.getFirstName());
		admin.setEmail(signupForm.getEmail());
		
		//Entityデータadmin(姓・名・メルアド・パスワード)をRepositoryはDBに保存
		adminRepository.save(admin);
	}
	

}
