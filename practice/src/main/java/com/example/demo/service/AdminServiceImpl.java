package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	//AfminRepositoryを利用できるようにするため
	@Autowired
	private AdminRepository adminRepository;
	
	//AdminServiceインターフェースにて宣言したメソッドの内容記述
	@Override
	public void register(Admin admin) {
		
		//
		adminRepository.save(admin);
	}
	

}
